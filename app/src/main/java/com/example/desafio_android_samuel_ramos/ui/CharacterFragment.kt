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
    private lateinit var binding: CharacterFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        characterViewModel = ViewModelProvider(this, CharacterViewModelFactory())
            .get(CharacterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = CharacterFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        binding.apply { viewModel = characterViewModel }
        context ?: return binding.root

        val adapter = CharacterAdapter()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
        subscribeUi(adapter)
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(adapter: CharacterAdapter) {
        characterViewModel.characterLiveData.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            binding.progressBar.hide()
        })
    }
}