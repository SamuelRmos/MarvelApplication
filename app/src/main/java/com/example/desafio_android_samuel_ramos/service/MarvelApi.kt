package com.example.desafio_android_samuel_ramos.service

import com.example.desafio_android_samuel_ramos.data.DataResponse
import com.example.desafio_android_samuel_ramos.data.ResponseComics
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("v1/public/characters")
    fun getCharacter(
        @Query("ts") ts: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): Deferred<Response<DataResponse>>

    @GET("/v1/public/characters/{characterId}/comics")
    fun getComics(
        @Path("characterId") characterId: Int,
        @Query("ts") ts: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): Deferred<Response<ResponseComics>>
}