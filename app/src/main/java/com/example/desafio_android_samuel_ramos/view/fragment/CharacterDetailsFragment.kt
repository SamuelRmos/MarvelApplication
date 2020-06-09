package com.example.desafio_android_samuel_ramos.view.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.transition.Slide
import androidx.transition.TransitionInflater
import com.example.desafio_android_samuel_ramos.R
import com.example.desafio_android_samuel_ramos.databinding.CharacterDetailsFragmentBinding
import com.example.desafio_android_samuel_ramos.extensions.show
import com.example.desafio_android_samuel_ramos.model.CharacterData
import com.example.desafio_android_samuel_ramos.model.Characters
import com.example.desafio_android_samuel_ramos.view.viewmodel.CharacterDetailsViewModel
import com.example.desafio_android_samuel_ramos.view.viewmodel.CharacterViewModelFactory

class CharacterDetailsFragment : Fragment() {

    private val characterViewModelFactory = CharacterViewModelFactory()
    private lateinit var binding: CharacterDetailsFragmentBinding
    private val args: CharacterDetailsFragmentArgs by navArgs()

    private val mViewModel: CharacterDetailsViewModel by lazy {
        ViewModelProvider(this, characterViewModelFactory)
            .get(CharacterDetailsViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        sharedElementEnterTransition =
            TransitionInflater.from(context)
                .inflateTransition(R.transition.image_shared_element_transition)
//
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        startPostponedEnterTransition()
        binding = CharacterDetailsFragmentBinding.inflate(
            inflater, container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind(args.character, mViewModel.createOnClickListener())
    }

    private fun bind(item: Characters, listener: View.OnClickListener) {
        binding.apply {
            character = item
            clickListener = listener
            executePendingBindings()
        }
        CharacterData.character = item
        binding.btnHQ.show()
    }
}