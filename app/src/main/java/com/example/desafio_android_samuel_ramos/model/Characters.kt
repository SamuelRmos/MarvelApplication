package com.example.desafio_android_samuel_ramos.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Characters")
data class Characters(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail?
)
