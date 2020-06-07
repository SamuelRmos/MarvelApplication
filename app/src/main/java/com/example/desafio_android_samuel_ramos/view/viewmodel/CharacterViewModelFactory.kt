package com.example.desafio_android_samuel_ramos.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.desafio_android_samuel_ramos.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinComponent
import org.koin.core.inject

class CharacterViewModelFactory : ViewModelProvider.Factory, KoinComponent {

    private val characterRepository: CharacterRepository by inject()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(CharacterViewModel::class.java) -> {
                CharacterViewModel(Dispatchers.Main, Dispatchers.IO, characterRepository) as T
            }
            modelClass.isAssignableFrom(CharacterDetailsViewModel::class.java) -> {
                CharacterDetailsViewModel() as T
            }
            modelClass.isAssignableFrom(ComicViewModel::class.java) -> {
                ComicViewModel(Dispatchers.Main, Dispatchers.IO, characterRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}