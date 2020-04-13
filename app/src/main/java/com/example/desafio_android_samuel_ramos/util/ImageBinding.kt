package com.example.desafio_android_samuel_ramos.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.desafio_android_samuel_ramos.data.Characters
import com.example.desafio_android_samuel_ramos.data.Comic
import com.example.desafio_android_samuel_ramos.data.Comics

object ImageBinding {

    @JvmStatic
    @BindingAdapter("loadPicture")
    fun ImageView.setImage(image: Characters?) {
        image?.let {
            Glide.with(context)
                .load(it.thumbnail.path
                        + Constants.sizeImage
                        + it.thumbnail.extension)
                .into(this)
        }
    }

    @JvmStatic
    @BindingAdapter("loadImage")
    fun ImageView.setImage(image: Comic?) {
        image?.let {
            Glide.with(context)
                .load(it.thumbnail!!.path
                        + Constants.sizeImage
                        + it.thumbnail!!.extension)
                .into(this)
        }
    }
}