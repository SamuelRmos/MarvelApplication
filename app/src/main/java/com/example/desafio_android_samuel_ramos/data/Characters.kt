package com.example.desafio_android_samuel_ramos.data

data class Characters(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)

data class Thumbnail(
    val path: String,
    val extension: String
)

object CharacterData {
    var character: Characters? = null
}