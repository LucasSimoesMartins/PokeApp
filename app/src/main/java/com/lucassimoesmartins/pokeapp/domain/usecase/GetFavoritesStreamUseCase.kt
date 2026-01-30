package com.lucassimoesmartins.pokeapp.domain.usecase

import com.lucassimoesmartins.pokeapp.data.repository.PokemonRepository
import com.lucassimoesmartins.pokeapp.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

class GetFavoritesStreamUseCase(private val repository: PokemonRepository) {
    operator fun invoke(): Flow<List<Pokemon>> = repository.getFavoritesStream()
}