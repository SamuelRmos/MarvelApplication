package com.example.desafio_android_samuel_ramos.service

import com.example.desafio_android_samuel_ramos.data.CharactersResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface MarvelApi {
    @GET("/v1/public/characters")
    fun getCharacter(): Deferred<Response<CharactersResponse>>
}