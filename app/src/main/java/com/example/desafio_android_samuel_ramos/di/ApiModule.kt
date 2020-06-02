package com.example.desafio_android_samuel_ramos.di

import androidx.databinding.library.BuildConfig
import com.example.desafio_android_samuel_ramos.network.MarvelApi
import com.example.desafio_android_samuel_ramos.util.baseUrl
import com.example.desafio_android_samuel_ramos.util.hashKey
import com.example.desafio_android_samuel_ramos.util.marvelApiKey
import com.example.desafio_android_samuel_ramos.util.ts
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val ApiModule = module {

    single {
        val authInterceptor = Interceptor { chain ->
            val newUrl = chain.request().url
                .newBuilder()
                .addQueryParameter("ts", ts)
                .addQueryParameter("apikey", marvelApiKey)
                .addQueryParameter("hash", hashKey)
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()
            chain.proceed(newRequest)
        }

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client =
            if (BuildConfig.DEBUG) {
                OkHttpClient().newBuilder()
                    .addInterceptor(authInterceptor)
                    .addInterceptor(loggingInterceptor)
                    .build()
            } else {
                OkHttpClient().newBuilder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(authInterceptor)
                    .build()
            }

        Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
    factory { get<Retrofit>().create(MarvelApi::class.java) }
}