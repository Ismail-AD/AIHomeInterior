package org.yourappdev.homeinterior.navigation

sealed class Routes {
    data object OnBoarding : Routes()
    data object BaseAppScreen : Routes()
    data object Splash : Routes()
    data object AddScreen : Routes()
    data object Create : Routes()
    data object Explore : Routes()
    data object Files : Routes()
    data object FileEdit : Routes()
    data object AbtToGenerate : Routes()
    data object Account : Routes()
    data object Subscription : Routes()
    data object Result : Routes()
    data object Profile : Routes()
}