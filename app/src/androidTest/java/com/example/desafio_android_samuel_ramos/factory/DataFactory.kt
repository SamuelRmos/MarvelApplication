package com.example.desafio_android_samuel_ramos.factory

import com.example.desafio_android_samuel_ramos.model.Characters
import com.example.desafio_android_samuel_ramos.model.Thumbnail

class DataFactory {

    companion object {
        fun randomList(): MutableList<Characters>? {
            val character = Characters(
                id = 1011334,
                name = "3-D Man",
                description = "",
                thumbnail = Thumbnail( "ddddd", "sssss")
            )
            val list = mutableListOf<Characters>()
            list.add(character)
            return list
        }
    }
}