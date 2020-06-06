package com.example.desafio_android_samuel_ramos.view.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.desafio_android_samuel_ramos.base.BaseTest
import com.example.desafio_android_samuel_ramos.di.configureTestAppComponent
import com.example.desafio_android_samuel_ramos.model.DataResponse
import com.example.desafio_android_samuel_ramos.model.ResponseComics
import com.example.desafio_android_samuel_ramos.repository.CharacterRepository
import com.google.gson.Gson
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.mockito.junit.MockitoJUnitRunner

@RunWith(JUnit4::class)
class ComicViewModelTest : BaseTest() {
    //region constants

    //end region constants

    //region helper fields

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val mDispatcher = Dispatchers.Unconfined

    private val mTitle = "Hulk (2008) #54"
    private val description = "Mayan Gods! End of the world as we know it! Guest starring Alpha Flight, Machine Man, She-Hulks, A-Bomb!"
    private val mPrice = "4.99"

    private lateinit var sut: ComicViewModel
    private val mCharacterRepo = mockk<CharacterRepository>(relaxed = true)

    // end region helper fields

    @Before
    fun setup() {
        super.setUp()
        startKoin { modules(configureTestAppComponent(getMockWebServerUrl())) }
    }

    @Test
    fun `comicViewModel dataPopulates expectedValue`() = runBlocking {

        val sampleResponse = getJson("comic_list")
        val jsonObj = Gson().fromJson(sampleResponse, ResponseComics::class.java)

        coEvery { mCharacterRepo.getComics() } returns jsonObj.data.results.toMutableList()

        sut = ComicViewModel(mDispatcher, mDispatcher, mCharacterRepo)
        sut.mComicLiveData.observeForever { }
        sut.fetchComics()

        assert(sut.mComicLiveData.value != null)

        val dataReceived = sut.mComicLiveData.value

        assertNotNull(dataReceived)
        assertEquals(dataReceived?.title, mTitle)
        assertEquals(dataReceived?.description, description)
        assertEquals(dataReceived?.prices, mPrice)
    }



    // region helper methods

    // end region helper methods

    // region helper class

    // end region helper class
}