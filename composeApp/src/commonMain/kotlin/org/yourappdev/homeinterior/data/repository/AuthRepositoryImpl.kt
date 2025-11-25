package org.yourappdev.homeinterior.data.repository

import io.ktor.client.call.body
import org.yourappdev.homeinterior.data.local.dao.ProfileDao
import org.yourappdev.homeinterior.data.local.entities.UserInfoEntity
import org.yourappdev.homeinterior.data.remote.service.AuthService
import org.yourappdev.homeinterior.domain.model.RegisterRequest
import org.yourappdev.homeinterior.domain.model.RegisterResponse
import org.yourappdev.homeinterior.domain.model.VerifyResponse
import org.yourappdev.homeinterior.domain.repo.AuthRepository

class AuthRepositoryImpl(val authService: AuthService, val userProfileDao: ProfileDao) : AuthRepository {
    override suspend fun register(request: RegisterRequest): RegisterResponse {
        val response = authService.register(request).body<RegisterResponse>()
        return response
    }

    override suspend fun verifyOtp(
        email: String,
        otp: String
    ): VerifyResponse {
        val response = authService.verifyOtp(email = email, otp = otp).body<VerifyResponse>()
        if (response.success && response.user != null) {
            userProfileDao.addUserInfo(
                UserInfoEntity(
                    id = response.user.id,
                    email = response.user.email,
                    fullname = response.user.fullname,
                    token = response.token
                )
            )
        }
        return response
    }

    override suspend fun resendOtp(email: String): VerifyResponse {
        val response = authService.resendOtp(email = email).body<VerifyResponse>()
        return response
    }


    override suspend fun login(
        email: String,
        password: String
    ): VerifyResponse {
        val response = authService.login(email = email, password = password).body<VerifyResponse>()
        if (response.success && response.user != null) {
            userProfileDao.addUserInfo(
                UserInfoEntity(
                    id = response.user.id,
                    email = response.user.email,
                    fullname = response.user.fullname,
                    token = response.token
                )
            )
        }
        return response
    }
    override suspend fun forgetPasswordRequest(email: String): RegisterResponse {
        val response = authService
            .forgetPasswordRequest(email)
            .body<RegisterResponse>()

        return response
    }

    override suspend fun forgetPasswordVerify(
        email: String,
        otp: String
    ): RegisterResponse {
        val response = authService
            .forgetPasswordVerify(email = email, otp = otp)
            .body<RegisterResponse>()

        return response
    }

    override suspend fun forgetPasswordReset(
        email: String,
        password: String,
        confirm_password: String
    ): RegisterResponse {

        val response = authService
            .forgetPasswordReset(
                email = email,
                password = password,
                confirm_password = confirm_password
            )
            .body<RegisterResponse>()

        return response
    }


}