package com.example.desafio_android_samuel_ramos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.desafio_android_samuel_ramos.viewmodel.CharacterViewModel
import com.example.desafio_android_samuel_ramos.databinding.CharacterFragmentBinding
import com.example.desafio_android_samuel_ramos.extensions.hide
import com.example.desafio_android_samuel_ramos.viewmodel.CharacterViewModelFactory

class CharacterFragment : Fragment() {

    private lateinit var characterViewModel: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val characterViewModelFactory = CharacterViewModelFactory()
        characterViewModel = ViewModelProvider(this, characterViewModelFactory)
            .get(CharacterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = CharacterFragmentBinding.inflate(inflater, container, false)
            .apply {
                viewModel = characterViewModel
            }
        context ?: return binding.root

        val adapter = CharacterAdapter()

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
        subscribeUi(binding, adapter)
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(binding: CharacterFragmentBinding, adapter: CharacterAdapter) {
        characterViewModel.characterLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
                binding.progressBar.hide()
            }
        })
    }
}