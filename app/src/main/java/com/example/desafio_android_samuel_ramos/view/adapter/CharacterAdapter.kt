package com.example.desafio_android_samuel_ramos.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_samuel_ramos.model.Characters
import com.example.desafio_android_samuel_ramos.databinding.ItemLayoutBinding
import com.example.desafio_android_samuel_ramos.view.fragment.CharacterFragmentDirections

class CharacterAdapter(val context: Context, list: MutableList<Characters>) :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private var items = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    fun updateCharactersList(characters: List<Characters>) {
        items.clear()
        items.addAll(characters)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = items[position]
        holder.apply {
            bind(createOnClickListener(character.id), character)
            itemView.tag = position
        }
    }

    override fun getItemCount(): Int = items.size

    private fun createOnClickListener(characterId: Int): View.OnClickListener {
        return View.OnClickListener {
            val direction =
                CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailsFragment(
                    characterId
                )
            it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, item: Characters) {
            binding.apply {
                clickListener = listener
                character = item
                executePendingBindings()
            }
        }
    }
}
