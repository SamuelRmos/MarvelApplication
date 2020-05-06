package com.example.desafio_android_samuel_ramos.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio_android_samuel_ramos.CharacterApplication
import com.example.desafio_android_samuel_ramos.model.Characters
import com.example.desafio_android_samuel_ramos.persistence.CharacterDao
import com.example.desafio_android_samuel_ramos.repository.CharacterRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CharacterViewModel(
    private val characterRepository: CharacterRepository,
    private val characterDao: CharacterDao,
    private val characterApplication: CharacterApplication
) : ViewModel() {
    val characterLiveData = fetchCharacters()

    private fun fetchCharacters(): MutableLiveData<MutableList<Characters>> {
        val characterList = MutableLiveData<MutableList<Characters>>()
        val characters = characterDao.getCharacterList()
        val isConnected = isNetworkAvailable(characterApplication)

        runBlocking {
            if ((characters.isEmpty() && isConnected) || isConnected) {
                characterList.postValue(characterRepository.getCharacter())
                characterDao.insertCharacterList(characterRepository.getCharacter())
            } else characterList.postValue(characterDao.getCharacterList())
        }
        return characterList
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo: NetworkInfo?
        activeNetworkInfo = cm.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }

}