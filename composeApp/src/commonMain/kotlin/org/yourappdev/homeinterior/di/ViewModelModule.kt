package org.yourappdev.homeinterior.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.yourappdev.homeinterior.ui.Authentication.AuthViewModel

val viewModelModule = module {
    viewModelOf(::AuthViewModel)
}