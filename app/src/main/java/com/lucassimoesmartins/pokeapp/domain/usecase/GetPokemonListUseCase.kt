package com.lucassimoesmartins.pokeapp.domain.usecase

import com.lucassimoesmartins.pokeapp.data.PokemonRepository
import com.lucassimoesmartins.pokeapp.domain.model.OperationResult
import com.lucassimoesmartins.pokeapp.domain.model.Pokemon

class FetchPokemonListUseCase(private val repository: PokemonRepository) {
    operator fun invoke(onResult: (OperationResult<List<Pokemon>>) -> Unit) {
        repository.fetchPokemonList(onResult)
    }
}