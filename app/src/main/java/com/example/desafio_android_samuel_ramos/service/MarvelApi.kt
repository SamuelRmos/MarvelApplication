package com.example.desafio_android_samuel_ramos.service

import com.example.desafio_android_samuel_ramos.model.DataResponse
import com.example.desafio_android_samuel_ramos.model.ResponseComics
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelApi {

    @GET("v1/public/characters")
    fun getCharacter(): Deferred<Response<DataResponse>>

    @GET("/v1/public/characters/{characterId}/comics")
    fun getComics(@Path("characterId") characterId: Int):
            Deferred<Response<ResponseComics>>
}