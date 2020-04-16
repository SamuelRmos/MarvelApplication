package com.example.desafio_android_samuel_ramos.service

import androidx.databinding.library.BuildConfig
import com.example.desafio_android_samuel_ramos.util.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitFactory {
    private val authInterceptor = Interceptor { chain ->
        val  newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("ts", Constants.ts)
            .addQueryParameter("apikey", Constants.marvelApiKey)
            .addQueryParameter("hash", Constants.hashKey)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()
        chain.proceed(newRequest)
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client =
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

    fun retrofit(baseURL : String) : Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(baseURL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}