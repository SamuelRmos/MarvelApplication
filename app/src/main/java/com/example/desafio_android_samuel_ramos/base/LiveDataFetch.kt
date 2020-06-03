package com.example.desafio_android_samuel_ramos.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LiveDataFetch<T>(
    val responseStatus: ResponseStatus,
    val response: LiveData<T>? = null,
    val error: Throwable? = null
) {

    enum class ResponseStatus {
        SUCCESS, LOADING, ERROR
    }

    companion object {
        fun <T> loading() = LiveDataFetch<T>(ResponseStatus.LOADING)
        fun <T> success(data: LiveData<T>) = LiveDataFetch(ResponseStatus.SUCCESS, data)
        fun <T> error(err: Throwable) = LiveDataFetch<T>(ResponseStatus.ERROR, null, err)
    }
}