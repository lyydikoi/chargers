package com.example.ergoen.di

import com.example.ergoen.ui.MainActivityViewModel
import com.example.ergoen.ui.auth.LoginViewModel
import com.example.ergoen.ui.chargers.ChargersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MainActivityViewModel(get(), get()) }
    viewModel { ChargersViewModel(get(), get()) }
    viewModel { LoginViewModel(get()) }
}