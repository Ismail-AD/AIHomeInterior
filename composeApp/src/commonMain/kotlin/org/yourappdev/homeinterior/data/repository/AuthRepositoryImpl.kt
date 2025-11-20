package org.yourappdev.homeinterior.data.repository

import io.ktor.client.call.body
import org.yourappdev.homeinterior.data.remote.service.AuthService
import org.yourappdev.homeinterior.domain.model.RegisterRequest
import org.yourappdev.homeinterior.domain.model.RegisterResponse
import org.yourappdev.homeinterior.domain.repo.AuthRepository

class AuthRepositoryImpl(val authService: AuthService) : AuthRepository {
    override suspend fun register(request: RegisterRequest): RegisterResponse {
        val response = authService.register(request).body<RegisterResponse>()
        return response
    }
}