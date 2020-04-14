package com.example.desafio_android_samuel_ramos.serviceTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.desafio_android_samuel_ramos.repository.CharacterRepository
import com.example.desafio_android_samuel_ramos.service.MarvelApi
import com.example.desafio_android_samuel_ramos.service.RetrofitFactory
import com.example.desafio_android_samuel_ramos.util.Constants
import com.example.desafio_android_samuel_ramos.viewmodel.CharacterViewModel
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class ServiceTest {

    @get:Rule
    val intantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var characterViewModel: CharacterViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.characterViewModel = CharacterViewModel()
    }

    @Test
    fun fetchCharacters() {
        runBlocking {
            val marvelApi: MarvelApi = RetrofitFactory
                .retrofit(Constants.baseUrl)
                .create(MarvelApi::class.java)
            val characters = CharacterRepository(marvelApi).getCharacter()
            assertNotNull(characters)
        }
    }

    @Test
    fun fetchComics() {
        runBlocking {
            val marvelApi: MarvelApi = RetrofitFactory
                .retrofit(Constants.baseUrl)
                .create(MarvelApi::class.java)
            val comics = CharacterRepository(marvelApi).getComics()
            assertNotNull(comics)
        }
    }
}
