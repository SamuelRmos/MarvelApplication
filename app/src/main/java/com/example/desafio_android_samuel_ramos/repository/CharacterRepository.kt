package com.example.desafio_android_samuel_ramos.repository

import com.example.desafio_android_samuel_ramos.CharacterApplication
import com.example.desafio_android_samuel_ramos.data.*
import com.example.desafio_android_samuel_ramos.di.ApiComponent
import com.example.desafio_android_samuel_ramos.service.MarvelApi
import com.example.desafio_android_samuel_ramos.util.Constants
import retrofit2.Retrofit
import javax.inject.Inject

class CharacterRepository: BaseRepository() {
    @Inject
    lateinit var retrofit: Retrofit

    init {
        val apiComponent: ApiComponent = CharacterApplication.apiComponent
        apiComponent.inject(this)
    }
    suspend fun getCharacter(): MutableList<Characters>? {

        val api: MarvelApi = retrofit.create(MarvelApi::class.java)

        val characterResponse = safeApiCall(
            call = {
                api.getCharacter().await()
            },
            errorMessage = "Error Fetching Characters Response"
        )
        return characterResponse!!.data.results.toMutableList();
    }

    suspend fun getComics(): MutableList<Comics>? {

        val api: MarvelApi = retrofit.create(MarvelApi::class.java)

        val comicResponse = safeApiCall(
            call = {
                api.getComics(CharacterData.character!!.id).await()
            },
            errorMessage = "Error Fetching Characters Response"
        )
        return comicResponse!!.data.results.toMutableList()
    }
}