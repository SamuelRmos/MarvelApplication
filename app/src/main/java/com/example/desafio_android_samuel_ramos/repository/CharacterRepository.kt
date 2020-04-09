package com.example.desafio_android_samuel_ramos.repository

import com.example.desafio_android_samuel_ramos.data.Characters
import com.example.desafio_android_samuel_ramos.service.MarvelApi

class CharacterRepository(private val api: MarvelApi): BaseRepository() {

    suspend fun getCharacter() : MutableList<Characters>?{

        val characterResponse = safeApiCall(
            call = {api.getCharacter().await()},
            errorMessage = "Error Fetching Characters Response"
        )
        return characterResponse?.results?.toMutableList();
    }

}