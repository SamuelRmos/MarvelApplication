package com.example.desafio_android_samuel_ramos.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.desafio_android_samuel_ramos.model.CharacterData
import com.example.desafio_android_samuel_ramos.model.Characters
import com.example.desafio_android_samuel_ramos.databinding.CharacterDetailsFragmentBinding
import com.example.desafio_android_samuel_ramos.extensions.hide
import com.example.desafio_android_samuel_ramos.extensions.show
import com.example.desafio_android_samuel_ramos.util.messageError
import com.example.desafio_android_samuel_ramos.view.viewmodel.CharacterDetailsViewModel
import com.example.desafio_android_samuel_ramos.view.viewmodel.CharacterViewModelFactory

class CharacterDetailsFragment : Fragment() {

    private val characterViewModelFactory = CharacterViewModelFactory()
    private lateinit var binding: CharacterDetailsFragmentBinding

    private val mViewModel: CharacterDetailsViewModel by lazy {
        ViewModelProvider(this, characterViewModelFactory)
            .get(CharacterDetailsViewModel::class.java)
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
        processData()
        context ?: return binding.root
        return binding.root
    }

    private fun processData() {

        mViewModel.fetchData(arguments.let {
            CharacterDetailsFragmentArgs.fromBundle(
                it!!
            ).characterId
        })

        mViewModel.mDetailResponse.observe(viewLifecycleOwner, Observer { item ->
            when (item) {
                null -> Toast.makeText(activity, messageError, Toast.LENGTH_SHORT).show()
                else -> bind(item, mViewModel.createOnClickListener())
            }
        })
        mViewModel.mLoadingLiveData.observe(viewLifecycleOwner, loadingObserver)
    }

    private fun bind(item: Characters, listener: View.OnClickListener) {

        val refresh = Handler(Looper.getMainLooper())
        refresh.post {
            binding.apply {
                character = item
                clickListener = listener
                executePendingBindings()
            }
            CharacterData.character = item
        }
    }

    private val loadingObserver = Observer<Boolean> { visible ->
        when {
            visible -> binding.progressBar.show()
            else -> {
                binding.progressBar.hide()
                binding.btnHQ.show()
            }
        }
    }
}