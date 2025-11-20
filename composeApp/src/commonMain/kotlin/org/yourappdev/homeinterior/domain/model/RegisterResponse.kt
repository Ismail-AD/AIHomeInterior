package org.yourappdev.homeinterior.domain.model

data class RegisterResponse(
    val success: Boolean,
    val message: String,
    val error: String? = ""
)