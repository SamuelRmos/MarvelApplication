package com.example.desafio_android_samuel_ramos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.desafio_android_samuel_ramos.data.CharacterData
import com.example.desafio_android_samuel_ramos.data.Characters
import com.example.desafio_android_samuel_ramos.databinding.CharacterDetailsFragmentBinding
import com.example.desafio_android_samuel_ramos.util.hide
import com.example.desafio_android_samuel_ramos.util.show
import com.example.desafio_android_samuel_ramos.viewmodel.CharacterDetailsViewModel
import com.example.desafio_android_samuel_ramos.viewmodel.CharacterViewModel

class CharacterDetailsFragment : Fragment() {

    private lateinit var viewmodel: CharacterDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewmodel = ViewModelProvider(this).get(CharacterDetailsViewModel::class.java)
        val binding = CharacterDetailsFragmentBinding.inflate(
            inflater, container,
            false
        )
        context ?: return binding.root
        subscribeUi(binding)
        return binding.root
    }

    private fun subscribeUi(binding: CharacterDetailsFragmentBinding) {
        viewmodel.fetchCharacters()
        viewmodel.characterLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                bind(binding, it.get(arguments?.let { it1 ->
                    CharacterDetailsFragmentArgs.fromBundle(it1).position
                }!!), viewmodel.createOnClickListener())
                binding.progressBar.hide()
                binding.btnHQ.show()
            }
        })
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