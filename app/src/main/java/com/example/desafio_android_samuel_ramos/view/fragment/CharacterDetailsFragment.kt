package com.example.desafio_android_samuel_ramos.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.desafio_android_samuel_ramos.model.CharacterData
import com.example.desafio_android_samuel_ramos.model.Characters
import com.example.desafio_android_samuel_ramos.databinding.CharacterDetailsFragmentBinding
import com.example.desafio_android_samuel_ramos.extensions.hide
import com.example.desafio_android_samuel_ramos.extensions.show
import com.example.desafio_android_samuel_ramos.view.viewmodel.CharacterDetailsViewModel
import com.example.desafio_android_samuel_ramos.view.viewmodel.CharacterViewModelFactory

class CharacterDetailsFragment : Fragment() {

    private lateinit var viewmodel: CharacterDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val characterViewModelFactory = CharacterViewModelFactory()
        viewmodel = ViewModelProvider(this, characterViewModelFactory)
            .get(CharacterDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = CharacterDetailsFragmentBinding.inflate(
            inflater, container,
            false
        )
        context ?: return binding.root
        subscribeUi(binding)
        return binding.root
    }

    private fun subscribeUi(binding: CharacterDetailsFragmentBinding) {
        val character = viewmodel.getCharacter(arguments.let {
            CharacterDetailsFragmentArgs.fromBundle(
                it!!
            ).characterId
        })
        bind(binding, character, viewmodel.createOnClickListener())
        binding.progressBar.hide()
        binding.btnHQ.show()
    }

    private fun bind(
        binding: CharacterDetailsFragmentBinding,
        item: Characters,
        listener: View.OnClickListener
    ) {
        binding.apply {
            character = item
            clickListener = listener
            executePendingBindings()
        }
        CharacterData.character = item
    }
}