package com.example.desafio_android_samuel_ramos.view.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.desafio_android_samuel_ramos.repository.DetailRepository
import com.example.desafio_android_samuel_ramos.view.fragment.CharacterDetailsFragmentDirections

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