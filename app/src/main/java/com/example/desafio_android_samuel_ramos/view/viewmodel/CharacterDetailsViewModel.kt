package com.example.desafio_android_samuel_ramos.view.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.desafio_android_samuel_ramos.base.LiveDataFetch
import com.example.desafio_android_samuel_ramos.base.LiveDataWrapper
import com.example.desafio_android_samuel_ramos.model.Characters
import com.example.desafio_android_samuel_ramos.repository.DetailRepository
import com.example.desafio_android_samuel_ramos.view.fragment.CharacterDetailsFragmentDirections
import java.lang.Exception

class CharacterDetailsViewModel(
    private val detailRepository: DetailRepository
) : ViewModel() {

    val mLoadingLiveData = MutableLiveData<Boolean>()
    var mDetailResponse = MutableLiveData<LiveDataFetch<Characters>>()

    fun fetchData(id: Int) {

        if (mDetailResponse.value == null) {

            mDetailResponse.value = LiveDataFetch.loading()
            mLoadingLiveData.postValue(true)

            try {
                val data = detailRepository.getCharacterById(id)
                val result = MutableLiveData<Characters>()
                result.postValue(data)
                try {
                    mDetailResponse.value = LiveDataFetch.success(result)
                } catch (e: Exception) {
                }
                mLoadingLiveData.postValue(false)
            } catch (e: Exception) {
                mLoadingLiveData.postValue(false)
                mDetailResponse.value = LiveDataFetch.error(e)
            }
        }
    }

    fun createOnClickListener(): View.OnClickListener {
        return View.OnClickListener {
            it.findNavController().navigate(
                CharacterDetailsFragmentDirections
                    .actionCharacterDetailsFragmentToComicFragment()
            )
        }
    }
}