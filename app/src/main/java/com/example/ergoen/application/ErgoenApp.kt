package com.example.ergoen.application

import android.app.Application
import com.example.ergoen.di.appModule
import com.example.ergoen.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ErgoenApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    // Start Koin
    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@ErgoenApp)
            modules(listOf(appModule, presentationModule))
        }
    }
}
