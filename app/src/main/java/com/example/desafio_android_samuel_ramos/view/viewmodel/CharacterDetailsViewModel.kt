package com.example.desafio_android_samuel_ramos.view.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.desafio_android_samuel_ramos.model.Characters
import com.example.desafio_android_samuel_ramos.repository.DetailRepository
import com.example.desafio_android_samuel_ramos.view.fragment.CharacterDetailsFragmentDirections
import kotlinx.coroutines.*
import java.lang.Exception

class CharacterDetailsViewModel(
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher,
    private val detailRepository: DetailRepository
) : ViewModel() {

    private val job = SupervisorJob()
    private val mUiScope = CoroutineScope(mainDispatcher + job)
    private val mIoScope = CoroutineScope(ioDispatcher + job)

    val mLoadingLiveData = MutableLiveData<Boolean>()
    var mDetailResponse = MutableLiveData<Characters>()

    fun fetchData(id: Int) {

        if (mDetailResponse.value == null) {

            mUiScope.launch {

                mLoadingLiveData.postValue(true)
                try {
                    val data = mIoScope.async {
                        return@async detailRepository.getCharacterById(id)
                    }.await()

                    try {
                        mDetailResponse.value = data
                    } catch (e: Exception) {
                    }
                    mLoadingLiveData.postValue(false)
                } catch (e: Exception) {
                    mLoadingLiveData.postValue(false)
                    mDetailResponse.value = null
                }
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