package com.example.desafio_android_samuel_ramos.app

import com.example.desafio_android_samuel_ramos.CharacterApplication
import org.koin.core.module.Module

class TestMainApplication: CharacterApplication() {
    override fun provideDependency() = listOf<Module>()
}