package com.example.desafio_android_samuel_ramos.di

import com.example.desafio_android_samuel_ramos.AppModule
import com.example.desafio_android_samuel_ramos.repository.CharacterRepository
import com.example.desafio_android_samuel_ramos.ui.CharacterFragment
import com.example.desafio_android_samuel_ramos.viewmodel.CharacterViewModel
import com.example.desafio_android_samuel_ramos.viewmodel.CharacterViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface ApiComponent {
    fun inject(characterRepository: CharacterRepository)
    fun inject(characterViewModel: CharacterViewModel)
    fun inject(characterViewModelFactory: CharacterViewModelFactory)
}