package com.example.desafio_android_samuel_ramos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.desafio_android_samuel_ramos.CharacterApplication
import com.example.desafio_android_samuel_ramos.di.ApiComponent
import com.example.desafio_android_samuel_ramos.repository.CharacterRepository
import javax.inject.Inject

class CharacterViewModelFactory : ViewModelProvider.Factory {

    @Inject
    lateinit var characterRepository: CharacterRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val apiComponent: ApiComponent = CharacterApplication.apiComponent
        apiComponent.inject(this)

        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            return CharacterViewModel(characterRepository) as T
        } else if (modelClass.isAssignableFrom(CharacterDetailsViewModel::class.java)) {
            return CharacterDetailsViewModel(characterRepository) as T
        } else if (modelClass.isAssignableFrom(ComicViewModel::class.java)) {
            return ComicViewModel(characterRepository) as T
        } else throw IllegalArgumentException("Unknown ViewmModel class")
    }
}