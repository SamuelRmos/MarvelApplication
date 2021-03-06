package com.example.desafio_android_samuel_ramos.binding

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.desafio_android_samuel_ramos.model.Characters
import com.example.desafio_android_samuel_ramos.model.Comic
import com.example.desafio_android_samuel_ramos.util.sizeImage
import com.example.desafio_android_samuel_ramos.util.sizeWide

object ImageBinding {

    @JvmStatic
    @BindingAdapter("loadWide")
    fun ImageView.setWide(image: Characters?) {
        image?.let {
            Glide.with(context)
                .load(it.thumbnail.path
                        + sizeWide
                        + it.thumbnail.extension)
                .centerCrop()
                .into(this)
        }
    }

    @JvmStatic
    @BindingAdapter("loadImage")
    fun ImageView.setImage(image: Comic?) {
        image?.let {
            Glide.with(context)
                .load(it.thumbnail!!.path
                        + sizeImage
                        + it.thumbnail!!.extension)
                .into(this)
        }
    }
}