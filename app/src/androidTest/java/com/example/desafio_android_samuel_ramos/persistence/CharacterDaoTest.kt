package com.example.desafio_android_samuel_ramos.persistence

import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.desafio_android_samuel_ramos.factory.BufferFactory
import org.hamcrest.CoreMatchers.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterDaoTest : AppDatabaseTest() {

    private lateinit var characterDao: CharacterDao

    @Before
    fun init() {
        characterDao = appDataBase.charactersDao()
    }

    @Test
    fun characterDao_insertCharacter() {

        val cachedCharacter = BufferFactory.makeCachedCharacter()
        characterDao.insertCharacterList(cachedCharacter)

        val characterList = characterDao.getCharacterList()
        assertThat(characterList[0].name, `is`(cachedCharacter?.get(0)?.name))

        val character = characterDao.getCharacter(1011334)
        assertThat(character.name, `is`(cachedCharacter?.get(0)?.name))
    }

}