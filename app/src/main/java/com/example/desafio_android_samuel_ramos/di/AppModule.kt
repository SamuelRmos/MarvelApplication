package com.example.desafio_android_samuel_ramos.di

import com.example.desafio_android_samuel_ramos.CharacterApplication
import com.example.desafio_android_samuel_ramos.view.viewmodel.CharacterDetailsViewModel
import com.example.desafio_android_samuel_ramos.view.viewmodel.CharacterViewModel
import com.example.desafio_android_samuel_ramos.view.viewmodel.ComicViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    factory { CharacterApplication() }
    factory { Dispatchers.Main }
    factory { Dispatchers.IO }

    viewModel {
        CharacterViewModel(
            mainDispatcher = get(),
            ioDispatcher = get(),
            characterRepository = get()
        )
    }

    viewModel {
        ComicViewModel(
            mainDispatcher = get(),
            ioDispatcher = get(),
            characterRepository = get()
        )
    }

    viewModel {
        CharacterDetailsViewModel()
    }
}