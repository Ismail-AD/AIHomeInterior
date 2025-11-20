package org.yourappdev.homeinterior.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.yourappdev.homeinterior.data.remote.httpClient
import org.yourappdev.homeinterior.data.remote.service.AuthService
import org.yourappdev.homeinterior.data.repository.AuthRepositoryImpl
import org.yourappdev.homeinterior.navigation.Routes
import org.yourappdev.homeinterior.ui.Authentication.LoginScreen
import org.yourappdev.homeinterior.ui.Authentication.Register.RegisterRoot
import org.yourappdev.homeinterior.ui.Authentication.Register.RegisterViewModel
import org.yourappdev.homeinterior.ui.Authentication.VerificationScreen
import org.yourappdev.homeinterior.ui.Authentication.WelcomeScreen
import org.yourappdev.homeinterior.ui.BottomBarScreen.BaseBottomBarScreen
import org.yourappdev.homeinterior.ui.OnBoarding.BaseScreen
import org.yourappdev.homeinterior.ui.OnBoarding.SplashScreen
import org.yourappdev.homeinterior.ui.theme.AppTypography

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    MaterialTheme(typography = AppTypography()) {
        NavHost(navController, startDestination = Routes.Welcome) {
            composable<Routes.Welcome> {
                WelcomeScreen(onLogin = {
                    navController.navigate(Routes.Login)
                })
            }
            composable<Routes.Login> {
                LoginScreen(onRegister = {
                    navController.navigate(Routes.Register)
                })
            }
            composable<Routes.Splash> {
                SplashScreen() {
                    navController.navigate(Routes.OnBoarding)
                }
            }
            composable<Routes.Verification> {
                VerificationScreen { }
            }
            composable<Routes.Register> {
                val viewModel: RegisterViewModel = viewModel<RegisterViewModel> {
                    RegisterViewModel(AuthRepositoryImpl(AuthService(client = httpClient)))
                }
                RegisterRoot(viewModel, onRegisterSuccess = {
                    navController.navigate(Routes.Verification)
                })
            }
            composable<Routes.OnBoarding> {
                BaseScreen() {
                    navController.navigate(Routes.BaseAppScreen)
                }
            }
            composable<Routes.BaseAppScreen> {
                BaseBottomBarScreen()
            }
        }
    }
}