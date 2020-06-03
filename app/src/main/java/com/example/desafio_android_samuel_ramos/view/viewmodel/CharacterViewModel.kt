package com.example.desafio_android_samuel_ramos.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio_android_samuel_ramos.base.LiveDataWrapper
import com.example.desafio_android_samuel_ramos.model.Characters
import com.example.desafio_android_samuel_ramos.model.DataResponse
import com.example.desafio_android_samuel_ramos.repository.CharacterRepository
import kotlinx.coroutines.*

class CharacterViewModel(
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher,
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val job = SupervisorJob()
    private val mUiScope = CoroutineScope(mainDispatcher + job)
    private val mIoScope = CoroutineScope(ioDispatcher + job)

    val mLoadingLiveData = MutableLiveData<Boolean>()
    var mCharacterResponse = MutableLiveData<LiveDataWrapper<Characters>>()

    fun requestData() {

        if (mCharacterResponse.value == null) {

            mUiScope.launch {

                mCharacterResponse.value = LiveDataWrapper.loading()
                setLoadingVisibility(true)

                try {
                    val data = mIoScope.async {
                        return@async characterRepository.getCharacter()
                    }.await()

                    try {
                        mCharacterResponse.value = LiveDataWrapper.success(data)
                    } catch (e: Exception) {
                    }
                    setLoadingVisibility(false)
                } catch (e: Exception) {
                    setLoadingVisibility(false)
                    mCharacterResponse.value = LiveDataWrapper.error(e)
                }
            }
        }
    }

    private fun setLoadingVisibility(visible: Boolean) {
        mLoadingLiveData.postValue(visible)
    }
}