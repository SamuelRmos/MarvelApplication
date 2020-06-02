package com.example.desafio_android_samuel_ramos.di

fun configureTestAppComponent(baseUrl: String) =
    listOf(
        MockWebServerTest,
        configureApiDependencyTest(baseUrl)
    )