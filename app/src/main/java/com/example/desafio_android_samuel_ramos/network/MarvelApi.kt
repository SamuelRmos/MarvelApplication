package com.example.desafio_android_samuel_ramos.network

import com.example.desafio_android_samuel_ramos.model.DataResponse
import com.example.desafio_android_samuel_ramos.model.ResponseComics
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelApi {

    @GET("v1/public/characters")
    fun getCharacterAsync(): Deferred<Response<DataResponse>>

    @GET("/v1/public/characters/{characterId}/comics")
    fun getComicsAsync(@Path("characterId") characterId: Int):
            Deferred<Response<ResponseComics>>
}