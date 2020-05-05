package com.example.desafio_android_samuel_ramos.di

import com.example.desafio_android_samuel_ramos.repository.CharacterRepository
import com.example.desafio_android_samuel_ramos.repository.DetailRepository
import com.example.desafio_android_samuel_ramos.viewmodel.CharacterDetailsViewModel
import com.example.desafio_android_samuel_ramos.viewmodel.CharacterViewModel
import com.example.desafio_android_samuel_ramos.viewmodel.CharacterViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules =
    [AppModule::class,
        ApiModule::class,
        PersistenceModule::class,
        DetailRepositoryModule::class
    ]
)
interface ApiComponent {
    fun inject(characterRepository: CharacterRepository)
    fun inject(characterViewModelFactory: CharacterViewModelFactory)
    fun inject(detailRepository: DetailRepository)
}