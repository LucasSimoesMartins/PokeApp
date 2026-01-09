package com.lucassimoesmartins.pokeapp.presentation

import android.graphics.Bitmap
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.lucassimoesmartins.pokeapp.R
import com.lucassimoesmartins.pokeapp.databinding.ItemPokemonBinding
import com.lucassimoesmartins.pokeapp.domain.model.Pokemon

class PokemonAdapter : ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(PokemonDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PokemonViewHolder(private val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: Pokemon) {
            binding.txtName.text = pokemon.name

            Glide.with(binding.root.context)
                .asBitmap()
                .load(pokemon.imageUrl)
                .placeholder(R.drawable.placeholder_pokemon)
                .listener(object : RequestListener<Bitmap> {
                    override fun onResourceReady(resource: Bitmap, model: Any, target: Target<Bitmap?>?, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                        Palette.from(resource).generate { palette ->
                            val color = palette?.getDominantColor(Color.LTGRAY) ?: Color.LTGRAY
                            binding.pokemonCard.setCardBackgroundColor(color)
                        }
                        return false
                    }

                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap?>, isFirstResource: Boolean): Boolean {
                        return false
                    }
                })
                .into(binding.imgPokemon)
        }
    }

    object PokemonDiffCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }
    }
}
