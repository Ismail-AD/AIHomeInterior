package org.yourappdev.homeinterior.data.local.settings

import com.russhwolf.settings.Settings

expect class SettingsFactory {
    fun createSettings(): Settings
}