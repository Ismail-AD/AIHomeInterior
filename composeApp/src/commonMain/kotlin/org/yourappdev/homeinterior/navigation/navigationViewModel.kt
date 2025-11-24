package org.yourappdev.homeinterior.navigation

import androidx.lifecycle.ViewModel
import com.russhwolf.settings.Settings
import com.russhwolf.settings.boolean
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.yourappdev.homeinterior.domain.repo.AuthRepository
import org.yourappdev.homeinterior.domain.repo.UserRepository
import org.yourappdev.homeinterior.utils.Constants.LOGIN
import org.yourappdev.homeinterior.utils.Constants.ONBOARDING

class NavigationViewModel(val settings: Settings) : ViewModel() {

    private val _state = MutableStateFlow(NavigationState())
    val state: StateFlow<NavigationState> = _state.asStateFlow()

    init {
        checkOnBoardStatus()
    }

    private fun checkOnBoardStatus() {
        val isLoggedIn = settings.getBoolean(LOGIN, defaultValue = false)
        val isOnboardingDone = settings.getBoolean(ONBOARDING, defaultValue = false)

        _state.value = when {
            isLoggedIn && !isOnboardingDone -> _state.value.copy(startDestination = Routes.OnBoarding.toString())
            isLoggedIn -> _state.value.copy(startDestination = Routes.BaseAppScreen.toString())
            else -> _state.value.copy(startDestination = Routes.Welcome.toString())
        }
    }

}