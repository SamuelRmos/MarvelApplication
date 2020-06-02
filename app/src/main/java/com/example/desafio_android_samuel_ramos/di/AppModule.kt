package com.example.desafio_android_samuel_ramos.di

import com.example.desafio_android_samuel_ramos.CharacterApplication
import org.koin.dsl.module

val AppModule = module {
    factory { CharacterApplication() }
}