package com.example.desafio_android_samuel_ramos.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio_android_samuel_ramos.model.Comic
import com.example.desafio_android_samuel_ramos.model.Comics
import com.example.desafio_android_samuel_ramos.repository.CharacterRepository
import kotlinx.coroutines.*

class ComicViewModel(
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher,
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val job = SupervisorJob()
    private val mUiScope = CoroutineScope(mainDispatcher + job)
    private val mIoScope = CoroutineScope(ioDispatcher + job)
    private lateinit var mComic: Comic

    val mLoadingLiveData = MutableLiveData<Boolean>()
    val mComicLiveData = MutableLiveData<Comic>()

    fun fetchComics() {

        if (mComicLiveData.value == null) {

            mUiScope.launch {
                setLoadingVisibility(true)

                try {
                    val data = mIoScope.async {
                        return@async characterRepository.getComics()
                    }.await()

                    try {
                        val dataResult = dataComic(data)
                        mComicLiveData.value = dataResult
                    } catch (e: Exception) {
                    }
                    setLoadingVisibility(false)
                } catch (e: Exception) {
                    setLoadingVisibility(false)
                }
            }
        }
    }

    private fun dataComic(comics: MutableList<Comics>): Comic {

        var price = 0.00F
        if (comics.size != 0) {
            for (item in comics) {
                for (element in item.prices) {
                    if (element.price >= price) {
                        price = element.price
                        setComic(item, price)
                    }
                }
            }
        }
        return mComic
    }

    private fun setComic(comics: Comics, price: Float) {
        mComic = Comic(
            title = comics.title,
            description = comics.description,
            prices = price.toString(),
            thumbnail = comics.thumbnail
        )
    }

    private fun setLoadingVisibility(visible: Boolean) {
        mLoadingLiveData.postValue(visible)
    }

//    fun createOnClickListener(): View.OnClickListener {
//        return View.OnClickListener {
//            it.findNavController().navigate(
//                ComicFragmentDirections.actionComicFragmentToCharacterFragment()
//            )
//        }
//    }
}