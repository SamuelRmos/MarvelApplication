package com.example.desafio_android_samuel_ramos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.desafio_android_samuel_ramos.data.Comic
import com.example.desafio_android_samuel_ramos.data.ComicList
import com.example.desafio_android_samuel_ramos.databinding.ComicFragmentBinding
import com.example.desafio_android_samuel_ramos.util.hide
import com.example.desafio_android_samuel_ramos.util.show
import com.example.desafio_android_samuel_ramos.viewmodel.ComicViewModel

class ComicFragment : Fragment() {

    private lateinit var viewModel: ComicViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ComicViewModel::class.java)
        val binding = ComicFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root
        subscribeUi(binding)
        return binding.root
    }

    private fun subscribeUi(binding: ComicFragmentBinding) {
        viewModel.fetchComics()
        viewModel.comicLiveData.observe(viewLifecycleOwner, Observer {
            it.let {
                bind(binding, it, viewModel.createOnClickListener())
            }
        })
    }

    private fun bind(binding: ComicFragmentBinding, item: Comic, listener: View.OnClickListener) {
        binding.apply {
            comic = item
            clickListener = listener
            executePendingBindings()
        }
        binding.progressBar.hide()
        binding.btnHome.show()
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