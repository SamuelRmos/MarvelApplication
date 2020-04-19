package com.example.desafio_android_samuel_ramos

import dagger.Module
import dagger.Provides

@Module
class AppModule constructor(private var characterApplication: CharacterApplication) {
    @Provides
    fun provideCharacterApplication(): CharacterApplication = characterApplication
}