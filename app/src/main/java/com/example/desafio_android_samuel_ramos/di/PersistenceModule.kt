package com.example.desafio_android_samuel_ramos.di

import androidx.room.Room
import com.example.desafio_android_samuel_ramos.CharacterApplication
import com.example.desafio_android_samuel_ramos.R
import com.example.desafio_android_samuel_ramos.persistence.AppDataBase
import com.example.desafio_android_samuel_ramos.persistence.CharacterDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {
    
    @Singleton
    @Provides
    fun provideRoomDatabase(application: CharacterApplication): AppDataBase = Room.databaseBuilder(
        application,
        AppDataBase::class.java,
        application.getString(R.string.marvel_db))
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideMovieDao(appDataBase: AppDataBase): CharacterDao = appDataBase.charactersDao()
    
}