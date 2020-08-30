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
import com.example.desafio_android_samuel_ramos.base.LiveDataWrapper
import com.example.desafio_android_samuel_ramos.databinding.CharacterFragmentBinding
import com.example.desafio_android_samuel_ramos.extensions.hide
import com.example.desafio_android_samuel_ramos.extensions.show
import com.example.desafio_android_samuel_ramos.extensions.waitForTransition
import com.example.desafio_android_samuel_ramos.model.Characters
import com.example.desafio_android_samuel_ramos.view.adapter.CharacterAdapter
import com.example.desafio_android_samuel_ramos.view.viewmodel.CharacterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterFragment : Fragment() {

    private lateinit var mCharacterAdapter: CharacterAdapter
    private lateinit var binding: CharacterFragmentBinding
    private val mViewModel  by viewModel<CharacterViewModel>()

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
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mCharacterAdapter = CharacterAdapter(requireActivity(), arrayListOf())
        binding.recyclerView.adapter = mCharacterAdapter
        waitForTransition(binding.recyclerView)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.mCharacterResponse.observe(viewLifecycleOwner, mDataObserver)
        mViewModel.mLoadingLiveData.observe(viewLifecycleOwner, loadingObserver)
        mViewModel.requestData()
    }

    private val mDataObserver = Observer<LiveDataWrapper<Characters>> { result ->
        when (result.responseStatus) {
            LiveDataWrapper.ResponseStatus.LOADING -> {

            }
            LiveDataWrapper.ResponseStatus.ERROR -> {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
            LiveDataWrapper.ResponseStatus.SUCCESS -> {
                processData(result.response!!)
            }
        }
    }

    private fun processData(lisItems: MutableList<Characters>) {
        val refresh = Handler(Looper.getMainLooper())
        refresh.post {
            mCharacterAdapter.updateCharactersList(lisItems)
        }
    }

    private val loadingObserver = Observer<Boolean> { visible ->
        when {
            visible -> binding.progressBar.show()
            else -> binding.progressBar.hide()
        }
    }
}