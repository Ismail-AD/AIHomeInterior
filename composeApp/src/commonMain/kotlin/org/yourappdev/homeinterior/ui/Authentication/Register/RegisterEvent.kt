package org.yourappdev.homeinterior.ui.Authentication.Register

sealed interface RegisterEvent {
    data class NameUpdate(val name: String) : RegisterEvent
    data class EmailUpdate(val email: String) : RegisterEvent
    data class PasswordUpdate(val password: String) : RegisterEvent
    data class OTPUpdate(val otp: String) : RegisterEvent
    data object Register : RegisterEvent
    data object Verify : RegisterEvent
    data class TogglePassword(val newState: Boolean) : RegisterEvent
}