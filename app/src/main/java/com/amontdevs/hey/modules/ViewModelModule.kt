package com.amontdevs.hey.modules

import com.amontdevs.hey.ui.homenav.friends.FriendsViewModel
import com.amontdevs.hey.ui.homenav.home.HomeViewModel
import com.amontdevs.hey.ui.homenav.settings.SettingsViewModel
import com.amontdevs.hey.ui.loading.LoadingViewModel
import com.amontdevs.hey.ui.splash.SplashViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LoadingViewModel(get(),get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { FriendsViewModel(get()) }
    viewModel { SettingsViewModel(androidApplication())}
}