package com.example.desafio_android_samuel_ramos.di

fun generateTestAppComponent(baseApi: String) =
    listOf(
        configureNetworkDependencyTest(baseApi),
        MockServerTest,
        CharacterModule,
        PersistenceModule
    )