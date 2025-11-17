package org.yourappdev.homeinterior.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
sealed class Routes {

    @Serializable
    @SerialName("onboarding")
    data object OnBoarding : Routes()

    @Serializable
    @SerialName("home")
    data object BaseAppScreen : Routes()

    @Serializable
    @SerialName("splash")
    data object Splash : Routes()

    @Serializable
    @SerialName("add")
    data object AddScreen : Routes()

    @Serializable
    @SerialName("create")
    data object Create : Routes()

    @Serializable
    @SerialName("explore")
    data object Explore : Routes()

    @Serializable
    @SerialName("files")
    data object Files : Routes()

    @Serializable
    @SerialName("file-edit")
    data object FileEdit : Routes()

    @Serializable
    @SerialName("generate")
    data object AbtToGenerate : Routes()

    @Serializable
    @SerialName("account")
    data object Account : Routes()

    @Serializable
    @SerialName("subscription")
    data object Subscription : Routes()

    @Serializable
    @SerialName("result")
    data object Result : Routes()

    @Serializable
    @SerialName("profile")
    data object Profile : Routes()
}