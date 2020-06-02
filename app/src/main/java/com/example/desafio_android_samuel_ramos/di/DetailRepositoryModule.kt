package com.example.desafio_android_samuel_ramos.di

import com.example.desafio_android_samuel_ramos.repository.DetailRepository
import org.koin.dsl.module

val DetailRepositoryModule = module {
    factory {
        DetailRepository(get())
    }
}