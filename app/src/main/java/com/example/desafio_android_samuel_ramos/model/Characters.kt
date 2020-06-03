package com.example.desafio_android_samuel_ramos.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Characters")
data class Characters(
    @PrimaryKey var id: Int,
    var name: String,
    var description: String,
    var thumbnail: Thumbnail?
)
