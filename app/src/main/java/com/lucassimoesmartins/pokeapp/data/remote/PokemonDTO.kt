package com.lucassimoesmartins.pokeapp.data.remote

import com.google.gson.annotations.SerializedName

data class PokemonDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val detailUrl: String
)