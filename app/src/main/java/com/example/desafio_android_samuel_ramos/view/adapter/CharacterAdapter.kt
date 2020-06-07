package com.example.desafio_android_samuel_ramos.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_samuel_ramos.databinding.ItemLayoutBinding
import com.example.desafio_android_samuel_ramos.extensions.toTransitionGroup
import com.example.desafio_android_samuel_ramos.model.Characters
import com.example.desafio_android_samuel_ramos.view.fragment.CharacterFragmentDirections

class CharacterAdapter(val context: Context, list: MutableList<Characters>) :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private var items = list
    private lateinit var binding: ItemLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    fun updateCharactersList(characters: List<Characters>) {
        items.clear()
        items.addAll(characters)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = items[position]
        holder.apply {
            bind(character)
            itemView.tag = position
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Characters) {
            binding.clickListener = View.OnClickListener {
                val direction =
                    CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailsFragment(
                        item
                    )
                val extras = FragmentNavigatorExtras(
                    binding.ivCharacter.toTransitionGroup(),
                    binding.tvCharacterName.toTransitionGroup()
                )
                it.findNavController().navigate(direction, extras)
            }
            binding.character = item
            binding.executePendingBindings()
        }
    }
}
