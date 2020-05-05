package com.example.desafio_android_samuel_ramos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.desafio_android_samuel_ramos.CharacterApplication
import com.example.desafio_android_samuel_ramos.di.ApiComponent
import com.example.desafio_android_samuel_ramos.persistence.CharacterDao
import com.example.desafio_android_samuel_ramos.repository.CharacterRepository
import com.example.desafio_android_samuel_ramos.repository.DetailRepository
import javax.inject.Inject

class CharacterViewModelFactory : ViewModelProvider.Factory {

    @Inject
    lateinit var characterRepository: CharacterRepository

    @Inject
    lateinit var characterDao: CharacterDao

    @Inject
    lateinit var characterApplication: CharacterApplication

    @Inject
    lateinit var detailRepository: DetailRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val apiComponent: ApiComponent = CharacterApplication.apiComponent
        apiComponent.inject(this)

        return when {
            modelClass.isAssignableFrom(CharacterViewModel::class.java) -> {
                CharacterViewModel(characterRepository, characterDao, characterApplication) as T
            }
            modelClass.isAssignableFrom(CharacterDetailsViewModel::class.java) -> {
                CharacterDetailsViewModel(detailRepository) as T
            }
            modelClass.isAssignableFrom(ComicViewModel::class.java) -> {
                ComicViewModel(characterRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewmModel class")
        }
    }
}