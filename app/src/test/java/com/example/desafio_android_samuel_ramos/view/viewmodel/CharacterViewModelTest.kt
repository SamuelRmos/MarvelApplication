package com.example.desafio_android_samuel_ramos.view.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.desafio_android_samuel_ramos.base.BaseTest
import com.example.desafio_android_samuel_ramos.base.LiveDataWrapper
import com.example.desafio_android_samuel_ramos.di.configureTestAppComponent
import com.example.desafio_android_samuel_ramos.model.DataResponse
import com.example.desafio_android_samuel_ramos.repository.CharacterRepository
import com.google.gson.Gson
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin

@RunWith(JUnit4::class)
class CharacterViewModelTest : BaseTest() {
    //region constants

    //end region constants

    //region helper fields

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mDispatcher = Dispatchers.Unconfined
    private val mCharacterRepo = mockk<CharacterRepository>(relaxed = true)

    private val mId = 1011334
    private val mName = "3-D Man"
    private val mDescription = ""

    private lateinit var sut: CharacterViewModel

    // end region helper fields

    @Before
    fun setup() {
        super.setUp()
        startKoin { modules(configureTestAppComponent(getMockWebServerUrl())) }
    }

    @Test
    fun `characterViewModel dataPopulates expectedValue`() = runBlocking {

        val sampleResponse = getJson("data_list")
        val jsonObj = Gson().fromJson(sampleResponse, DataResponse::class.java)

        coEvery { mCharacterRepo.getCharacter() } returns jsonObj.data.results.toMutableList()
        sut = CharacterViewModel(mDispatcher, mDispatcher, mCharacterRepo)

        sut.mCharacterResponse.observeForever { }
        sut.requestData()

        assert(sut.mCharacterResponse.value != null)
        assert(sut.mCharacterResponse.value!!.responseStatus
                == LiveDataWrapper.ResponseStatus.SUCCESS)

        val testResult = sut.mCharacterResponse.value

        assertEquals(testResult!!.response?.get(0)?.id, mId)
        assertEquals(testResult.response?.get(0)?.name, mName)
        assertEquals(testResult.response?.get(0)?.description, mDescription)
    }

    // region helper methods

    // end region helper methods

    // region helper class

    // end region helper class
}