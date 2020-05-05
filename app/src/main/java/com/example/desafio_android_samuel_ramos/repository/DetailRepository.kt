package com.example.desafio_android_samuel_ramos.repository

import com.example.desafio_android_samuel_ramos.CharacterApplication
import com.example.desafio_android_samuel_ramos.di.ApiComponent
import com.example.desafio_android_samuel_ramos.persistence.CharacterDao
import javax.inject.Inject

class DetailRepository {
    @Inject
    lateinit var characterDao: CharacterDao

    init {
        val apiComponent: ApiComponent = CharacterApplication.apiComponent
        apiComponent.inject(this)
    }

    fun getCharacterById(id: Int) = characterDao.getCharacter(id)
}