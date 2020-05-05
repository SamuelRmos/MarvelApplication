package com.example.desafio_android_samuel_ramos.model

data class DataComics(
    val limit : Int,
    val results: List<Comics>
)

data class ResponseComics(
    val data: DataComics
)