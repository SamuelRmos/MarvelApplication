package com.example.desafio_android_samuel_ramos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafio_android_samuel_ramos.R
import com.example.desafio_android_samuel_ramos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}
