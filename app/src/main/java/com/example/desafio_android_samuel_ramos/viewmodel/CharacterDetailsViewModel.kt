package com.example.desafio_android_samuel_ramos.viewmodel

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.desafio_android_samuel_ramos.data.Characters
import com.example.desafio_android_samuel_ramos.repository.CharacterRepository
import com.example.desafio_android_samuel_ramos.service.MarvelApi
import com.example.desafio_android_samuel_ramos.service.RetrofitFactory
import com.example.desafio_android_samuel_ramos.ui.CharacterDetailsFragmentDirections
import com.example.desafio_android_samuel_ramos.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CharacterDetailsViewModel : ViewModel() {

    private val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Default
    val characterLiveData = MutableLiveData<MutableList<Characters>>()

    fun fetchCharacters() {
        CoroutineScope(coroutineContext).launch {
            characterLiveData.postValue(
                CharacterRepository().getCharacter()
            )
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