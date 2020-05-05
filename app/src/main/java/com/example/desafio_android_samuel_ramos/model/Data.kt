package com.example.desafio_android_samuel_ramos.model

data class Data (
    val limit: Int,
    val results: List<Characters>

)

data class DataResponse(
    val data: Data
)