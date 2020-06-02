package com.example.desafio_android_samuel_ramos.di

import com.example.desafio_android_samuel_ramos.repository.CharacterRepository
import org.koin.dsl.module

val CharacterModule = module {
    factory {
        CharacterRepository(get())
    }
}