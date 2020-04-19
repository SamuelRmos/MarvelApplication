package com.example.desafio_android_samuel_ramos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio_android_samuel_ramos.data.Characters
import com.example.desafio_android_samuel_ramos.repository.CharacterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CharacterViewModel(private var characterRepository: CharacterRepository) : ViewModel() {
    private val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Default
    val characterLiveData = MutableLiveData<MutableList<Characters>>()

    fun fetchCharacters() {
        CoroutineScope(coroutineContext).launch {
            characterLiveData.postValue(
                characterRepository.getCharacter()
            )
        }
    }
}