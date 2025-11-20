package org.yourappdev.homeinterior.ui.Authentication.Register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.yourappdev.homeinterior.data.remote.util.ResultState
import org.yourappdev.homeinterior.domain.model.RegisterRequest
import org.yourappdev.homeinterior.domain.repo.AuthRepository

class RegisterViewModel(val repository: AuthRepository) : ViewModel() {
    private val _registerState = MutableStateFlow(RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()

    fun onRegisterFormEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EmailUpdate -> _registerState.value = _registerState.value.copy(email = event.email)

            is RegisterEvent.NameUpdate -> _registerState.value = _registerState.value.copy(username = event.name)

            is RegisterEvent.PasswordUpdate -> _registerState.value =
                _registerState.value.copy(password = event.password)

            RegisterEvent.Register -> {
                val request = RegisterRequest(
                    fullname = registerState.value.username,
                    email = registerState.value.email,
                    password = registerState.value.password
                )
                if (validateForm()) {
                    performRegister(request)
                }
            }

            is RegisterEvent.TogglePassword -> _registerState.value =
                _registerState.value.copy(showPassword = event.newState)
        }
    }

    private fun performRegister(request: RegisterRequest) {
        viewModelScope.launch {
            _registerState.value = _registerState.value.copy(registerResponse = ResultState.Loading)
            try {
                val response = repository.register(request)
                if (response.success) {
                    _registerState.value = _registerState.value.copy(registerResponse = ResultState.Success(response))
                } else {
                    response.error?.let {
                        _registerState.value =
                            _registerState.value.copy(registerResponse = ResultState.Failure(it))
                    }
                }
            } catch (e: Exception) {
                e.message?.let {
                    _registerState.value =
                        _registerState.value.copy(registerResponse = ResultState.Failure(it))
                }
            }
        }
    }

    private fun validateForm(): Boolean {
        val currentState = _registerState.value
        _registerState.value = currentState.copy(
            fieldError = null,
        )
        when {
            currentState.email.isBlank() -> {
                _registerState.value = _registerState.value.copy(fieldError = "Email is required")
                return false
            }

            currentState.username.isBlank() -> {
                _registerState.value = _registerState.value.copy(fieldError = "Name is required")
                return false
            }

            currentState.password.isBlank() -> {
                _registerState.value = _registerState.value.copy(fieldError = "Password is required")
                return false
            }
        }

        return true
    }


}