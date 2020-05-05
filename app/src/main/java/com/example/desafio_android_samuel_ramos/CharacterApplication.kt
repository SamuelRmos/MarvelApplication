package com.example.desafio_android_samuel_ramos

import android.app.Application
import android.content.Context
import com.example.desafio_android_samuel_ramos.di.*
import com.example.desafio_android_samuel_ramos.util.Constants

class CharacterApplication : Application() {
    companion object {
        var ctx: Context? = null
        lateinit var apiComponent: ApiComponent
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        apiComponent = initDaggerComponent()
    }

    private fun initDaggerComponent(): ApiComponent {
        apiComponent = DaggerApiComponent
            .builder()
            .apiModule(ApiModule(Constants.baseUrl))
            .persistenceModule(PersistenceModule())
            .detailRepositoryModule(DetailRepositoryModule())
            .appModule(AppModule(this))
            .build()
        return apiComponent
    }
}