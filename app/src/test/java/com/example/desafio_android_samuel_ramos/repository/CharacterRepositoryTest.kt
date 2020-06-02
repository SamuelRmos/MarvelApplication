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

    // end region helper fields

    @Before
    fun setup() {
        super.setUp()
        startKoin { modules(configureTestAppComponent(getMockWebServerUrl())) }

    }

    @Test
    fun `characterRepo retrieveData`() = runBlocking {
        mockNetworkResponseWithFileContent("data_list", HttpURLConnection.HTTP_OK)

        every { characterDao.getCharacterList() } returns mutableListOf()

        sut = CharacterRepository(characterDao)
        val dataReceived = sut.getCharacter()

        assertNotNull(dataReceived)
        assertEquals(dataReceived?.get(0)!!.id, mId)
        assertEquals(dataReceived[0].name, mName)
        assertEquals(dataReceived[0].description, mDescription)
    }

    // region helper methods

    // end region helper methods

    // region helper class

    // end region helper class
}