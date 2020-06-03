package com.example.desafio_android_samuel_ramos.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.desafio_android_samuel_ramos.model.Characters

object MockTestUtil {

    fun mockDetails() = Characters(
        id = 1011334,
        name = "3-D Man",
        description = "",
        thumbnail = null
    )
}