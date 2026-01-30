package com.lucassimoesmartins.pokeapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class PokemonEntity(
    @PrimaryKey
    val name: String,
    val imageUrl: String
)