package com.lucassimoesmartins.pokeapp.data.remote

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("next")
    val next: String?,
    @SerializedName("results")
    val results: List<PokemonDTO>
)