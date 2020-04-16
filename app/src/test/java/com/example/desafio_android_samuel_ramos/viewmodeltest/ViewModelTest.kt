package com.example.desafio_android_samuel_ramos.viewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.desafio_android_samuel_ramos.repository.CharacterRepository
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
class ViewModelTest {

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
    fun fetchCharacterResponse() {
        this.characterViewModel.fetchCharacters()
        this.characterViewModel.characterLiveData.observeForever {}

        assertNotNull(this.characterViewModel.characterLiveData.value)
    }
}