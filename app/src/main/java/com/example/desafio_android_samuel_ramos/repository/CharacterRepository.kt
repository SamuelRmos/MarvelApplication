package com.example.desafio_android_samuel_ramos.repository

import com.example.desafio_android_samuel_ramos.model.*
import com.example.desafio_android_samuel_ramos.network.MarvelApi
import com.example.desafio_android_samuel_ramos.persistence.CharacterDao
import org.koin.core.KoinComponent
import org.koin.core.inject

class CharacterRepository constructor(private val characterDao: CharacterDao) : BaseRepository(),
    KoinComponent {

    private val api: MarvelApi by inject()

    private val characterList = characterDao.getCharacterList()

    suspend fun getCharacter() = when {

        characterList.isEmpty() || characterList.size == 0 -> dataFetchLogic()
        else -> characterDao.getCharacterList()
    }

    private suspend fun dataFetchLogic(): MutableList<Characters> {

        val characterResponse = safeApiCall(
            call = {
                api.getCharacterAsync().await()
            },
            errorMessage = "Error Fetching Characters Response"
        )

        val dataReceived = characterResponse!!.data.results.toMutableList()
        characterDao.insertCharacterList(dataReceived)

        return dataReceived
    }

    suspend fun getComics(): MutableList<Comics> {

        val comicResponse = safeApiCall(
            call = {
                api.getComicsAsync(CharacterData.character!!.id).await()
            },
            errorMessage = "Error Fetching Characters Response"
        )

        return comicResponse!!.data.results.toMutableList()
    }
}