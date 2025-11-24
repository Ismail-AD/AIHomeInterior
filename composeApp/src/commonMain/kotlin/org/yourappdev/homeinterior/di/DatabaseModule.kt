package org.yourappdev.homeinterior.di

import com.russhwolf.settings.Settings
import org.koin.dsl.module
import org.yourappdev.homeinterior.data.local.AppDatabase


val databaseModule = module {
    single { get<AppDatabase>().userProfileDao() }
    single<Settings> { Settings() }

}