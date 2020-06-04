package com.example.desafio_android_samuel_ramos.app

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class CustomInstrumentationRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, TestMainApplication::class.java.name, context)
    }
}