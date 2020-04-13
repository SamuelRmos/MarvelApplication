package com.example.desafio_android_samuel_ramos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.desafio_android_samuel_ramos.R
import com.example.desafio_android_samuel_ramos.databinding.SplashFragmentBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SplashFragment : Fragment(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = SplashFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        launch {
            delay(3000)
            withContext(Dispatchers.Main) {
                movetoNext()
            }
        }
    }

    fun movetoNext() {
        val direction = SplashFragmentDirections.actionSplashFragmentToCharacterFragment()
        findNavController().navigate(direction)
    }
}