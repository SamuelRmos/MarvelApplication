package com.example.desafio_android_samuel_ramos.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.desafio_android_samuel_ramos.base.LiveDataFetch
import com.example.desafio_android_samuel_ramos.base.LiveDataWrapper
import com.example.desafio_android_samuel_ramos.model.CharacterData
import com.example.desafio_android_samuel_ramos.model.Characters
import com.example.desafio_android_samuel_ramos.databinding.CharacterDetailsFragmentBinding
import com.example.desafio_android_samuel_ramos.extensions.hide
import com.example.desafio_android_samuel_ramos.extensions.show
import com.example.desafio_android_samuel_ramos.view.viewmodel.CharacterDetailsViewModel
import com.example.desafio_android_samuel_ramos.view.viewmodel.CharacterViewModelFactory

class CharacterDetailsFragment : Fragment() {

    private val characterViewModelFactory = CharacterViewModelFactory()
    private lateinit var binding: CharacterDetailsFragmentBinding

    private val mViewModel: CharacterDetailsViewModel by lazy {
        ViewModelProvider(this, characterViewModelFactory)
            .get(CharacterDetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel.mDetailResponse.observe(this, mDataObserver)
        mViewModel.mLoadingLiveData.observe(this, loadingObserver)
        mViewModel.fetchData(arguments.let {
            CharacterDetailsFragmentArgs.fromBundle(
                it!!
            ).characterId
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = CharacterDetailsFragmentBinding.inflate(
            inflater, container,
            false
        )
        context ?: return binding.root
        return binding.root
    }

    private val mDataObserver = Observer<LiveDataFetch<Characters>> { result ->
        when (result.responseStatus) {
            LiveDataFetch.ResponseStatus.LOADING -> {

            }
            LiveDataFetch.ResponseStatus.ERROR -> {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
            LiveDataFetch.ResponseStatus.SUCCESS -> {
                bind(result.response?.value!!, mViewModel.createOnClickListener())
            }
        }
    }

//    private fun processData() {
//        val refresh = Handler(Looper.getMainLooper())
//        refresh.post {
//
//        }
//    }
//
//    private fun subscribeUi(binding: CharacterDetailsFragmentBinding) {
////        val character = viewmodel.getCharacter(arguments.let {
////            CharacterDetailsFragmentArgs.fromBundle(
////                it!!
////            ).characterId
////        })
////        bind(binding, character, viewmodel.createOnClickListener())
////        binding.progressBar.hide()
////        binding.btnHQ.show()
//    }

    private fun bind(
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

    private val loadingObserver = Observer<Boolean> { visible ->
        when {
            visible -> binding.progressBar.show()
            else -> binding.progressBar.hide()
        }
    }
}