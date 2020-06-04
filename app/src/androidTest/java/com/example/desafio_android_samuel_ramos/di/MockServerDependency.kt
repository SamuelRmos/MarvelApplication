package com.example.desafio_android_samuel_ramos.di

import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module

val MockServerTest = module {
    factory {
        MockWebServer()
    }
}
