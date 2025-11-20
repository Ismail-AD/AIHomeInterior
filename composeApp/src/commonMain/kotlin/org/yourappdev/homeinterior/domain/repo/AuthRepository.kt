package org.yourappdev.homeinterior.domain.repo

import org.yourappdev.homeinterior.domain.model.RegisterRequest
import org.yourappdev.homeinterior.domain.model.RegisterResponse

interface AuthRepository {
    suspend fun register(request: RegisterRequest): RegisterResponse
}