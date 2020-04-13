package com.example.desafio_android_samuel_ramos.repository

import com.example.desafio_android_samuel_ramos.data.*
import com.example.desafio_android_samuel_ramos.service.MarvelApi
import com.example.desafio_android_samuel_ramos.util.Constants

class CharacterRepository(private val api: MarvelApi) : BaseRepository() {

    suspend fun getCharacter(): MutableList<Characters>? {

        val characterResponse = safeApiCall(
            call = {
                api.getCharacter(
                    Constants.ts,
                    Constants.marvelApiKey,
                    Constants.hashKey
                ).await()
            },
            errorMessage = "Error Fetching Characters Response"
        )
        return characterResponse!!.data.results.toMutableList();
    }

    suspend fun getComics(): MutableList<Comics>? {
        val comicResponse = safeApiCall(
            call = {
                api.getComics(
                    CharacterData.character!!.id,
                    Constants.ts,
                    Constants.marvelApiKey,
                    Constants.hashKey
                ).await()
            },
            errorMessage = "Error Fetching Characters Response"
        )
        return comicResponse!!.data.results.toMutableList()
    }
}