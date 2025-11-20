package org.yourappdev.homeinterior.ui.Authentication.Register

import org.yourappdev.homeinterior.data.remote.util.ResultState
import org.yourappdev.homeinterior.domain.model.RegisterResponse

data class RegisterState(
    val registerResponse: ResultState<RegisterResponse> = ResultState.Stable,
    val email: String = "",
    val password: String = "",
    val username: String = "",
    val showPassword: Boolean = false,
    val fieldError: String? = null,
)