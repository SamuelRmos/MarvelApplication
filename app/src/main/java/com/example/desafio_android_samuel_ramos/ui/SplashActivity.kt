package com.example.desafio_android_samuel_ramos.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.desafio_android_samuel_ramos.databinding.SplashActivityBinding

class SplashActivity : AppCompatActivity(){
    private val SPLASH_TIME_OUT = 3000L

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val binding = SplashActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        moveNext()
    }

    private fun moveNext(){
        Handler().postDelayed(
            {
                val activityMain = Intent(this, MainActivity::class.java)
                startActivity(activityMain)
                finish()
            },SPLASH_TIME_OUT
        )
    }

}