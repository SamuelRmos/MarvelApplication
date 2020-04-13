package com.example.desafio_android_samuel_ramos.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.desafio_android_samuel_ramos.data.Comic
import com.example.desafio_android_samuel_ramos.data.ComicList
import com.example.desafio_android_samuel_ramos.data.Comics
import com.example.desafio_android_samuel_ramos.repository.CharacterRepository
import com.example.desafio_android_samuel_ramos.service.MarvelApi
import com.example.desafio_android_samuel_ramos.service.RetrofitFactory
import com.example.desafio_android_samuel_ramos.ui.ComicFragmentDirections
import com.example.desafio_android_samuel_ramos.util.Constants
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ComicViewModel : ViewModel() {

    private val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Default
    val comicLiveData = MutableLiveData<Comic>()
    private val marvelApi: MarvelApi = RetrofitFactory
        .retrofit(Constants.baseUrl)
        .create(MarvelApi::class.java)

    fun fetchComics() {
        CoroutineScope(coroutineContext).launch {
            comicLiveData.postValue(
                dataComic(CharacterRepository(marvelApi).getComics())
            )
        }
    }

    private fun dataComic(comics: MutableList<Comics>?): Comic {
        var price = 0.00F
        if (comics!!.size != 0) {
            for (item in comics) {
                for (element in item.prices) {
                    if (element.price >= price) {
                        price = element.price

                        setComic(item, price)
                    }
                }
            }
        }
        return Comic
    }

    private fun setComic(
        comics: Comics,
        price: Float
    ) {
        Comic.title = comics.title
        if (comics.description != null) {
            Comic.description = comics.description
        } else Comic.description = " "
        Comic.prices = price.toString()
        Comic.thumbnail = comics.thumbnail
    }

    fun createOnClickListener(): View.OnClickListener {
        return View.OnClickListener {
            it.findNavController().navigate(
                ComicFragmentDirections.actionComicFragmentToCharacterFragment()
            )
        }
    }
}