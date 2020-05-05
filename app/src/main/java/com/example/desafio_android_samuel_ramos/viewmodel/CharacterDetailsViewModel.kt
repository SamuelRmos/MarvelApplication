package com.example.desafio_android_samuel_ramos.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.desafio_android_samuel_ramos.model.Characters
import com.example.desafio_android_samuel_ramos.repository.CharacterRepository
import com.example.desafio_android_samuel_ramos.repository.DetailRepository
import com.example.desafio_android_samuel_ramos.ui.CharacterDetailsFragmentDirections
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CharacterDetailsViewModel(
    private val detailRepository: DetailRepository
) : ViewModel() {

    fun getCharacter(id: Int) = detailRepository.getCharacterById(id)

    fun createOnClickListener(): View.OnClickListener {
        return View.OnClickListener {
            it.findNavController().navigate(
                CharacterDetailsFragmentDirections
                    .actionCharacterDetailsFragmentToComicFragment()
            )
        }
    }
}