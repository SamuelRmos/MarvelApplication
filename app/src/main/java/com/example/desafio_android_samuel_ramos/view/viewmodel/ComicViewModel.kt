package com.example.desafio_android_samuel_ramos.view.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.desafio_android_samuel_ramos.model.Comic
import com.example.desafio_android_samuel_ramos.model.Comics
import com.example.desafio_android_samuel_ramos.repository.CharacterRepository
import com.example.desafio_android_samuel_ramos.view.fragment.ComicFragmentDirections
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ComicViewModel(private val characterRepository: CharacterRepository) : ViewModel() {

    private val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Default
    val comicLiveData = MutableLiveData<Comic>()

    fun fetchComics() {
        CoroutineScope(coroutineContext).launch {
            comicLiveData.postValue(
                dataComic(characterRepository.getComics())
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