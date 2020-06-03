package com.example.desafio_android_samuel_ramos.persistence

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class AppDatabaseTest {

    lateinit var appDataBase: AppDataBase

    @Before
    fun initDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDataBase = Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java).build()
    }

    @After
    fun closeDb() {
        appDataBase.close()
    }
}