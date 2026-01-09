package com.lucassimoesmartins.pokeapp.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val results: List<PokemonDTO>
)