package com.lucassimoesmartins.pokeapp.domain.usecase

import com.lucassimoesmartins.pokeapp.data.repository.PokemonRepository
import com.lucassimoesmartins.pokeapp.domain.model.Pokemon

class RemoveFavoriteUseCase(private val repository: PokemonRepository) {
    suspend operator fun invoke(pokemon: Pokemon) = repository.removeFavorite(pokemon)
}