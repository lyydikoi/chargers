package com.example.ergoen.di

import androidx.room.Room
import com.example.ergoen.data.UserRepositoryImpl
import com.example.ergoen.data.db.ErgoenDb
import com.example.ergoen.domain.repository.UserRepository
import com.example.ergoen.ui.auth.LoginViewModel
import com.example.ergoen.ui.utils.DATA_BASE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single {
        Room
            .databaseBuilder(androidContext(), ErgoenDb::class.java, DATA_BASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<ErgoenDb>().birdDao()
    }

    single<UserRepository> {
        UserRepositoryImpl(get())
    }

    /*viewModel {
        MainActivityViewModel()
    }*/

    viewModel { LoginViewModel() }

}