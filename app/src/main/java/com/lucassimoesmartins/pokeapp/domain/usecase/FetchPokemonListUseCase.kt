package com.lucassimoesmartins.pokeapp.domain.usecase

import com.lucassimoesmartins.pokeapp.data.PokemonRepository
import com.lucassimoesmartins.pokeapp.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

class FetchPokemonListUseCase(private val repository: PokemonRepository) {
    operator fun invoke(): Flow<List<Pokemon>> = repository.fetchPokemonList()
}