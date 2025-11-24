package org.yourappdev.homeinterior.data.local.settings

import com.russhwolf.settings.Settings
import java.util.prefs.Preferences

actual class SettingsFactory {
    actual fun createSettings(): Settings {
        val preferences = Preferences.userRoot().node("org.yourappdev.homeinterior")
        return PreferencesSettings(preferences)
    }
}