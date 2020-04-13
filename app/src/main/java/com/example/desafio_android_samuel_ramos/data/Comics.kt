package com.example.desafio_android_samuel_ramos.data

data class Comics(
    val id: Int,
    val title: String,
    val description: String,
    val prices: List<Price>,
    val thumbnail: Thumbnail
)

data class Price(
    val type: String,
    val price: Float
)

object Comic {
    var id: Int = 0
    var title: String = " "
    var description: String = " "
    var prices: String = " "
    var thumbnail: Thumbnail? = null
}