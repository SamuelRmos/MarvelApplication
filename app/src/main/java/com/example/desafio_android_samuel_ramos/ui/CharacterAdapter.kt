package com.example.desafio_android_samuel_ramos.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_samuel_ramos.model.Characters
import com.example.desafio_android_samuel_ramos.databinding.ItemLayoutBinding

class CharacterAdapter : ListAdapter<Characters, CharacterAdapter.ViewHolder>(
    DiffCallback()
) {
    private val items = mutableListOf<Characters>()

    override fun onBindViewHolder(holder: CharacterAdapter.ViewHolder, position: Int) {
        val character = items[position]
        holder.apply {
            bind(createOnClickListener(character.id), character)
            itemView.tag = position
        }
    }

    fun updateCharactersList(characters: List<Characters>){
        items.clear()
        items.addAll(characters)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    private fun createOnClickListener(characterId: Int): View.OnClickListener {
        return View.OnClickListener {
            val direction = CharacterFragmentDirections
                .actionCharacterFragmentToCharacterDetailsFragment(characterId)
            it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(
        private val binding: ItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, item: Characters) {
            binding.apply {
                clickListener = listener
                character = item
                executePendingBindings()
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Characters>() {
    override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean {
        return oldItem == newItem
    }
}