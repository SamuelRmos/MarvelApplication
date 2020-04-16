package com.example.desafio_android_samuel_ramos.repository

import com.example.desafio_android_samuel_ramos.data.*
import com.example.desafio_android_samuel_ramos.service.MarvelApi
import com.example.desafio_android_samuel_ramos.service.RetrofitFactory
import com.example.desafio_android_samuel_ramos.util.Constants

class CharacterRepository: BaseRepository() {

    private val api: MarvelApi = RetrofitFactory
        .retrofit(Constants.baseUrl)
        .create(MarvelApi::class.java)

    suspend fun getCharacter(): MutableList<Characters>? {

        val characterResponse = safeApiCall(
            call = {
                api.getCharacter().await()
            },
            errorMessage = "Error Fetching Characters Response"
        )
        return characterResponse!!.data.results.toMutableList();
    }

    suspend fun getComics(): MutableList<Comics>? {
        val comicResponse = safeApiCall(
            call = {
                api.getComics(CharacterData.character!!.id).await()
            },
            errorMessage = "Error Fetching Characters Response"
        )
        return comicResponse!!.data.results.toMutableList()
    }
}