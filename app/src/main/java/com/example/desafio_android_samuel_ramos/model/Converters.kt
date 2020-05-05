package com.example.desafio_android_samuel_ramos.model

import androidx.room.TypeConverter
import org.json.JSONObject

class Converters {
    @TypeConverter
    fun fromThumbnail(thumbnail: Thumbnail): String {
        return JSONObject().apply {
            put("extension", thumbnail.extension)
            put("path", thumbnail.path)
        }.toString()
    }

    @TypeConverter
    fun fromString(thumbnail: String): Thumbnail {
        val json = JSONObject(thumbnail)
        return Thumbnail(json.getString("path"), json.getString("extension"))
    }
}