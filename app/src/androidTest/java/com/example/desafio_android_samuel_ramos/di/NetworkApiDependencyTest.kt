package com.example.desafio_android_samuel_ramos.di

import com.example.desafio_android_samuel_ramos.network.MarvelApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun configureNetworkDependencyTest(baseUrl: String) =
    module {
        single {
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }
        factory { get<Retrofit>().create(MarvelApi::class.java) }
    }