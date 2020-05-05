package com.example.desafio_android_samuel_ramos.di

import com.example.desafio_android_samuel_ramos.repository.DetailRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DetailRepositoryModule {
    @Singleton
    @Provides
    fun provideDetailRepository(): DetailRepository = DetailRepository()
}