package org.yourappdev.homeinterior.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


const val BASE_URL = "https://interior.shabbirhussain.com/api/"

val httpClient = HttpClient {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
            }
        )
    }
    defaultRequest {
        header("Content-Type", "application/json")
        url(BASE_URL)
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                co.touchlab.kermit.Logger.i { "KTOR_LOG: $message" } // Console log
            }
        }
        level = LogLevel.ALL
        sanitizeHeader { header ->
            header == HttpHeaders.Authorization
        }
    }
}
