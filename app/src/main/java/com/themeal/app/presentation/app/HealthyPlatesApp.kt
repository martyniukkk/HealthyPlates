package com.themeal.app.presentation.app

import android.app.Application
import com.orhanobut.hawk.Hawk
import com.themeal.app.presentation.di.appModule
import com.themeal.app.presentation.di.dataModule
import com.themeal.app.presentation.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HealthyPlatesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
        startKoin {
            androidContext(this@HealthyPlatesApp)
            modules(listOf(appModule, domainModule, dataModule))
        }
    }
}