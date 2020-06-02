package com.example.desafio_android_samuel_ramos

import android.app.Application
import com.example.desafio_android_samuel_ramos.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class CharacterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
       startKoin {
           androidContext(this@CharacterApplication)
           modules(appComponent)
       }
    }
}