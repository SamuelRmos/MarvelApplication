package com.example.desafio_android_samuel_ramos.repository

import com.example.desafio_android_samuel_ramos.persistence.CharacterDao

class DetailRepository constructor(private val characterDao: CharacterDao) {
    suspend fun getCharacterById(id: Int) = characterDao.getCharacter(id)
}