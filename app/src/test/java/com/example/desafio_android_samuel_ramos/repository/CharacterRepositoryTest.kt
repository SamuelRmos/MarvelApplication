package com.example.desafio_android_samuel_ramos.repository

import com.example.desafio_android_samuel_ramos.base.BaseTest
import com.example.desafio_android_samuel_ramos.di.configureTestAppComponent
import com.example.desafio_android_samuel_ramos.persistence.CharacterDao
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class CharacterRepositoryTest : BaseTest() {
    //region constants

    //end region constants

    //region helper fields

    lateinit var sut: CharacterRepository

    private val characterDao = mockk<CharacterDao>(relaxed = true)

    private val mId = 1011334
    private val mName = "3-D Man"
    private val mDescription = ""

    private val mTitle = "Hulk (2008) #55"
    private val description = "The hands of the doomsday clock race towards MAYAN RULE! Former Avengers arrive to help stop the end of the world as more Mayan gods return. Rick \"A-Bomb\" Jones falls in battle!"

    // end region helper fields

    @Before
    fun setup() {
        super.setUp()
        startKoin { modules(configureTestAppComponent(getMockWebServerUrl())) }
    }

    @Test
    fun `characterRepo characterApi retrieveData`() = runBlocking {

        mockNetworkResponseWithFileContent("data_list", HttpURLConnection.HTTP_OK)
        every { characterDao.getCharacterList() } returns mutableListOf()

        sut = CharacterRepository(characterDao)
        val dataReceived = sut.getCharacter()

        assertNotNull(dataReceived)
        assertEquals(dataReceived[0].id, mId)
        assertEquals(dataReceived[0].name, mName)
        assertEquals(dataReceived[0].description, mDescription)
    }

    @Test
    fun `characterRepo comicApi retrieveData`() = runBlocking {

        mockNetworkResponseWithFileContent("comic_list", HttpURLConnection.HTTP_OK)
        every { characterDao.getCharacterList() } returns mutableListOf()

        sut = CharacterRepository(characterDao)
        val dataReceived = sut.getComics()

        assertNotNull(dataReceived)
        assertEquals(dataReceived[0].title, mTitle)
        assertEquals(dataReceived[0].description, description)
    }

    // region helper methods

    // end region helper methods

    // region helper class

    // end region helper class
}