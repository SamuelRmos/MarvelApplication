package com.example.desafio_android_samuel_ramos.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_samuel_ramos.model.Characters
import com.example.desafio_android_samuel_ramos.ui.CharacterAdapter

@BindingAdapter("adapterCharacterList")
fun bindAdapterCharacterList(view: RecyclerView, characters: List<Characters>?){
    characters?.let {
        (view.adapter as? CharacterAdapter)?.updateCharactersList(it)
    }
}