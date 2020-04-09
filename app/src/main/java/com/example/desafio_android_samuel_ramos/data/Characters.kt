package com.example.desafio_android_samuel_ramos.data

data class Characters(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: List<String>
)

data class CharactersResponse(
    val results: List<Characters>
)