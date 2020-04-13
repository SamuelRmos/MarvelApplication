package com.example.desafio_android_samuel_ramos.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_samuel_ramos.data.Characters
import com.example.desafio_android_samuel_ramos.databinding.ItemLayoutBinding

class CharacterAdapter : ListAdapter<Characters, CharacterAdapter.ViewHolder>(DiffCallback()) {
    override fun onBindViewHolder(holder: CharacterAdapter.ViewHolder, position: Int) {
        val character = getItem(position)
        holder.apply {
            bind(createOnClickListener(position), character)
            itemView.tag = character
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    private fun createOnClickListener(position: Int): View.OnClickListener {
        return View.OnClickListener {
            val direction = CharacterFragmentDirections
                .actionCharacterFragmentToCharacterDetailsFragment(position)
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