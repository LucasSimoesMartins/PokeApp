package com.lucassimoesmartins.pokeapp.data

import com.lucassimoesmartins.pokeapp.domain.model.OperationResult
import com.lucassimoesmartins.pokeapp.domain.model.Pokemon

interface PokemonRepository {
    fun fetchPokemonList(onResult: (OperationResult<List<Pokemon>>) -> Unit)
}