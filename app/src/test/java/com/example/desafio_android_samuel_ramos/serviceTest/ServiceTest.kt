package com.example.desafio_android_samuel_ramos.serviceTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.desafio_android_samuel_ramos.repository.CharacterRepository
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
    lateinit var repository: CharacterRepository

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        this.repository = CharacterRepository()
    }

    @Test
    fun fetchCharacters() {
        runBlocking {
            assertNotNull(repository.getCharacter())
        }
    }

    @Test
    fun fetchComics() {
        runBlocking {
            assertNotNull(repository.getComics())
        }
    }
}
