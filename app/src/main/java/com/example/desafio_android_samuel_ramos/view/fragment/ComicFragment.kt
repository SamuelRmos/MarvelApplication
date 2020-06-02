package com.example.desafio_android_samuel_ramos.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.desafio_android_samuel_ramos.databinding.ComicFragmentBinding
import com.example.desafio_android_samuel_ramos.extensions.hide
import com.example.desafio_android_samuel_ramos.extensions.show
import com.example.desafio_android_samuel_ramos.model.Comic
import com.example.desafio_android_samuel_ramos.view.viewmodel.CharacterViewModelFactory
import com.example.desafio_android_samuel_ramos.view.viewmodel.ComicViewModel

class ComicFragment : Fragment() {

    private lateinit var viewModel: ComicViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val characterViewModelFactory = CharacterViewModelFactory()
        viewModel = ViewModelProvider(this, characterViewModelFactory)
            .get(ComicViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ComicFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root
        subscribeUi(binding)
        return binding.root
    }

    private fun subscribeUi(binding: ComicFragmentBinding) {
        viewModel.fetchComics()
        viewModel.comicLiveData.observe(viewLifecycleOwner, Observer {
            bind(binding, it)
        })
    }

    private fun bind(binding: ComicFragmentBinding, item: Comic) {
        binding.apply {
            comic = item
            executePendingBindings()
        }
        binding.progressBar.hide()
        binding.tvprice.show()
    }

    private fun backtoDetails() {
        findNavController().navigate(
            ComicFragmentDirections.actionComicFragmentToCharacterFragment()
        )
        Toast.makeText (activity,
            "Don't exist comics to the character!",
            Toast.LENGTH_LONG
        ).show()
    }
}