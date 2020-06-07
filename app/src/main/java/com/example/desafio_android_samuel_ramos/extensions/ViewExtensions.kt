package com.example.desafio_android_samuel_ramos.extensions

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.material.button.MaterialButton

fun ProgressBar.hide(){
    visibility = View.GONE
}

fun ProgressBar.show(){
    visibility = View.VISIBLE
}

fun MaterialButton.show(){
    visibility = View.VISIBLE
}

fun TextView.show(){
    visibility = View.VISIBLE
}

fun View.toTransitionGroup() = this to transitionName