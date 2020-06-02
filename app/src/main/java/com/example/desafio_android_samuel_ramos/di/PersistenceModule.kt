package com.example.desafio_android_samuel_ramos.di

import androidx.room.Room
import com.example.desafio_android_samuel_ramos.R
import com.example.desafio_android_samuel_ramos.persistence.AppDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val PersistenceModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDataBase::class.java,
            androidApplication().getString(R.string.marvel_db)
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    factory { get<AppDataBase>().charactersDao() }
}