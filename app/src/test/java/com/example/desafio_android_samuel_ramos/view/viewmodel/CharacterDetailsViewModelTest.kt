package com.example.desafio_android_samuel_ramos.view.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.desafio_android_samuel_ramos.base.LiveDataFetch
import com.example.desafio_android_samuel_ramos.repository.DetailRepository
import com.example.desafio_android_samuel_ramos.utils.MockTestUtil.mockDetails
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CharacterDetailsViewModelTest {
    //region constants

    //end region constants

    //region helper fields

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: CharacterDetailsViewModel
    private val mDetailRepository = mockk<DetailRepository>(relaxed = true)

    private val mId = 1011334
    private val mName = "3-D Man"

    // end region helper fields

    @Before
    fun setup() {
    }

    @Test
    fun `characterDetailsViewModel fetchData`() {

        every { mDetailRepository.getCharacterById(1011334) } returns mockDetails()
        sut = CharacterDetailsViewModel(mDetailRepository)

        sut.mDetailResponse.observeForever { }
        sut.fetchData(1011334)

        assert(sut.mDetailResponse.value != null)
        assert(sut.mDetailResponse.value!!.responseStatus == LiveDataFetch.ResponseStatus.SUCCESS)

        val receivedData = sut.mDetailResponse.value

        assertNotNull(receivedData)
        assertEquals(receivedData?.response?.value?.id, mId)
        assertEquals(receivedData?.response?.value?.name, mName)
    }

    // region helper methods

    // end region helper methods

    // region helper class

    // end region helper class
}