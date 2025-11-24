package org.yourappdev.homeinterior.ui.Authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.yourappdev.homeinterior.data.remote.util.ResultState
import org.yourappdev.homeinterior.domain.model.RegisterRequest
import org.yourappdev.homeinterior.domain.repo.AuthRepository
import org.yourappdev.homeinterior.ui.Authentication.Register.RegisterEvent
import org.yourappdev.homeinterior.ui.Authentication.Register.RegisterState
import org.yourappdev.homeinterior.ui.common.base.CommonUiEvent
import org.yourappdev.homeinterior.utils.Constants.LOGIN

class AuthViewModel(val repository: AuthRepository, val settings: Settings) : ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state.asStateFlow()

    private val _uiEvent = MutableSharedFlow<CommonUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onRegisterFormEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EmailUpdate -> _state.value = _state.value.copy(email = event.email)

            is RegisterEvent.NameUpdate -> _state.value = _state.value.copy(username = event.name)

            is RegisterEvent.PasswordUpdate -> _state.value =
                _state.value.copy(password = event.password)

            RegisterEvent.Register -> {
                val request = RegisterRequest(
                    fullname = state.value.username,
                    email = state.value.email,
                    password = state.value.password
                )
                if (validateForm()) {
                    performRegister(request)
                }
            }

            is RegisterEvent.TogglePassword -> _state.value =
                _state.value.copy(showPassword = event.newState)

            RegisterEvent.Verify -> {
                val isBlank = state.value.otp.isBlank()
                if (isBlank) {
                    viewModelScope.launch {
                        _uiEvent.emit(CommonUiEvent.ShowError("OTP is required"))
                    }
                } else {
                    verifyOtp()
                }
            }

            is RegisterEvent.OTPUpdate -> _state.value = _state.value.copy(otp = event.otp)
        }
    }

    private fun verifyOtp() {
        viewModelScope.launch {
            _state.value = _state.value.copy(verifyResponse = ResultState.Loading)
            try {
                val response = repository.verifyOtp(
                    email = state.value.email,
                    otp = state.value.otp
                )

                if (response.success) {
                    _state.value = _state.value.copy(verifyResponse = ResultState.Success(response))
                    settings.putBoolean(LOGIN, true)
                    _uiEvent.emit(CommonUiEvent.ShowSuccess(response.message))
                    _uiEvent.emit(CommonUiEvent.NavigateToSuccess)
                } else {
                    response.error?.let {
                        _state.value = _state.value.copy(verifyResponse = ResultState.Failure(it))
                        _uiEvent.emit(CommonUiEvent.ShowError(it))
                    }
                }
            } catch (e: Exception) {
                e.message?.let {
                    _state.value = _state.value.copy(verifyResponse = ResultState.Failure(it))
                    _uiEvent.emit(CommonUiEvent.ShowError(it))
                }
            }
        }
    }

    private fun resendOtp() {
        viewModelScope.launch {
            _state.value = _state.value.copy(resendResponse = ResultState.Loading)
            try {
                val response = repository.resendOtp(email = state.value.email)

                if (response.success) {
                    _state.value = _state.value.copy(resendResponse = ResultState.Success(response))
                    _uiEvent.emit(CommonUiEvent.ShowSuccess("OTP resent successfully"))
                } else {
                    response.error?.let {
                        _state.value = _state.value.copy(resendResponse = ResultState.Failure(it))
                        _uiEvent.emit(CommonUiEvent.ShowError(it))
                    }
                }
            } catch (e: Exception) {
                e.message?.let {
                    _state.value = _state.value.copy(resendResponse = ResultState.Failure(it))
                    _uiEvent.emit(CommonUiEvent.ShowError(it))
                }
            }
        }
    }

    private fun performRegister(request: RegisterRequest) {
        viewModelScope.launch {
            _state.value = _state.value.copy(registerResponse = ResultState.Loading)
            try {
                val response = repository.register(request)
                if (response.success) {
                    _state.value = _state.value.copy(registerResponse = ResultState.Success(response))
                    _uiEvent.emit(CommonUiEvent.NavigateToSuccess)

                } else {
                    response.error?.let {
                        _state.value =
                            _state.value.copy(registerResponse = ResultState.Failure(it))
                        _uiEvent.emit(CommonUiEvent.ShowError(it))
                    }
                }
            } catch (e: Exception) {
                e.message?.let {
                    _state.value =
                        _state.value.copy(registerResponse = ResultState.Failure(it))
                    _uiEvent.emit(CommonUiEvent.ShowError(it))
                }
            }
        }
    }

    private fun validateForm(): Boolean {
        val currentState = _state.value

        val errorMessage = when {
            currentState.email.isBlank() -> "Email is required"
            currentState.username.isBlank() -> "Name is required"
            currentState.password.isBlank() -> "Password is required"
            else -> null
        }

        if (errorMessage != null) {
            viewModelScope.launch {
                _uiEvent.emit(CommonUiEvent.ShowError(errorMessage))
            }
            return false
        }

        return true
    }

}