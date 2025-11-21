package org.yourappdev.homeinterior.ui.Authentication.Register

import org.yourappdev.homeinterior.data.remote.util.ResultState
import org.yourappdev.homeinterior.domain.model.RegisterResponse
import org.yourappdev.homeinterior.domain.model.VerifyResponse

data class RegisterState(
    val registerResponse: ResultState<RegisterResponse> = ResultState.Stable,
    val resendResponse: ResultState<VerifyResponse> = ResultState.Stable,
    val verifyResponse: ResultState<VerifyResponse> = ResultState.Stable,
    val email: String = "",
    val password: String = "",
    val otp: String = "",
    val username: String = "",
    val showPassword: Boolean = false,
    val fieldError: String? = null,
)