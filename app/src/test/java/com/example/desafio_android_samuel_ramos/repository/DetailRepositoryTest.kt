package com.example.desafio_android_samuel_ramos.repository

import com.example.desafio_android_samuel_ramos.persistence.CharacterDao
import com.example.desafio_android_samuel_ramos.utils.MockTestUtil.mockDetails
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DetailRepositoryTest {
    //region constants

    //end region constants

    //region helper fields

    private lateinit var sut: DetailRepository
    private val mCharacterDao = mockk<CharacterDao>(relaxed = true)

    private val mId = 1011334
    private val mName = "3-D Man"

    // end region helper fields

    @Before
    fun setup() {
    }

    @Test
    fun `detailRepo getDetailData`() {

        every { mCharacterDao.getCharacter(1011334) } returns mockDetails()
        sut = DetailRepository(mCharacterDao)

        val receivedData = sut.getCharacterById(1011334)

        assertNotNull(receivedData)
        assertEquals(receivedData.id, mId)
        assertEquals(receivedData.name, mName)
    }

    // region helper methods

    // end region helper methods

    // region helper class

    // end region helper class
}