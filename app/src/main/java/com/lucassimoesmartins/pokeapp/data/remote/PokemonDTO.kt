package com.lucassimoesmartins.pokeapp.data.remote

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val detailUrl: String
)