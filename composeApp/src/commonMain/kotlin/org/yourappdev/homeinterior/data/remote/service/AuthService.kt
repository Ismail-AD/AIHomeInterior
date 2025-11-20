package org.yourappdev.homeinterior.data.remote.service

import io.ktor.client.HttpClient
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.parameters
import org.yourappdev.homeinterior.domain.model.RegisterRequest
import org.yourappdev.homeinterior.domain.model.RegisterResponse

class AuthService(val client: HttpClient) {
    suspend fun register(registerRequest: RegisterRequest) = client.post(
        "auth/register",
    ) {
        formData {
            append("fullname", registerRequest.fullname)
            append("email", registerRequest.email)
            append("password", registerRequest.password)
        }
    }
}