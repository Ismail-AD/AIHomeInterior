package org.yourappdev.homeinterior.di

import org.koin.dsl.module
import org.yourappdev.homeinterior.data.remote.service.AuthService
import org.yourappdev.homeinterior.data.repository.AuthRepositoryImpl
import org.yourappdev.homeinterior.domain.repo.AuthRepository

val dataModule = module {
    single { AuthService(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}