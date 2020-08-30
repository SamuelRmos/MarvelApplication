package com.example.desafio_android_samuel_ramos.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.desafio_android_samuel_ramos.databinding.ComicFragmentBinding
import com.example.desafio_android_samuel_ramos.extensions.hide
import com.example.desafio_android_samuel_ramos.extensions.show
import com.example.desafio_android_samuel_ramos.model.Comic
import com.example.desafio_android_samuel_ramos.util.messageError
import com.example.desafio_android_samuel_ramos.view.viewmodel.ComicViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicFragment : Fragment() {
    private lateinit var binding: ComicFragmentBinding
    private val mViewModel by viewModel<ComicViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ComicFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root
        processData()
        return binding.root
    }

    private fun processData() {

        mViewModel.fetchComics()

        mViewModel.mComicLiveData.observe(viewLifecycleOwner, Observer { item ->
            when (item) {
                null -> Toast.makeText(activity, messageError, Toast.LENGTH_SHORT).show()
                else -> bind(item)
            }
        })
        mViewModel.mLoadingLiveData.observe(viewLifecycleOwner, loadingObserver)
    }

    private fun bind(item: Comic) {
        binding.apply {
            comic = item
            executePendingBindings()
        }
    }

    private val loadingObserver = Observer<Boolean> { visible ->
        when {
            visible -> binding.progressBar.show()
            else -> {
                binding.progressBar.hide()
                binding.tvprice.show()
            }
        }
    }

    private fun backToDetails() {
        findNavController().navigate(
            ComicFragmentDirections.actionComicFragmentToCharacterFragment()
        )
        Toast.makeText(
            activity,
            "Don't exist comics to the character!",
            Toast.LENGTH_LONG
        ).show()
    }
}