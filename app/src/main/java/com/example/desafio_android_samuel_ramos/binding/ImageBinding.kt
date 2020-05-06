package com.example.desafio_android_samuel_ramos.binding

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.desafio_android_samuel_ramos.model.Characters
import com.example.desafio_android_samuel_ramos.model.Comic
import com.example.desafio_android_samuel_ramos.util.Constants

object ImageBinding {

    @JvmStatic
    @BindingAdapter("loadPicture")
    fun AppCompatImageView.setImage(image: Characters?) {
        image?.let {
            Glide.with(context)
                .load(it.thumbnail?.path
                        + Constants.sizeImage
                        + it.thumbnail?.extension)
                .centerCrop()
                .into(this)
        }
    }

    @JvmStatic
    @BindingAdapter("loadWide")
    fun AppCompatImageView.setWide(image: Characters?) {
        image?.let {
            Glide.with(context)
                .load(it.thumbnail?.path
                        + Constants.sizeWide
                        + it.thumbnail?.extension)
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
                        + Constants.sizeImage
                        + it.thumbnail!!.extension)
                .into(this)
        }
    }
}