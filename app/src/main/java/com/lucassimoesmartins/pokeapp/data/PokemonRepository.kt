package com.lucassimoesmartins.pokeapp.data

import com.lucassimoesmartins.pokeapp.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun fetchPokemonList(): Flow<List<Pokemon>>
}