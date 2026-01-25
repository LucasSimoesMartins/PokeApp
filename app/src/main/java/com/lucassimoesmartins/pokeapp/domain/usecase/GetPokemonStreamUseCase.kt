package com.lucassimoesmartins.pokeapp.domain.usecase

import androidx.paging.PagingData
import com.lucassimoesmartins.pokeapp.data.repository.PokemonRepository
import com.lucassimoesmartins.pokeapp.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

class GetPokemonStreamUseCase(private val repository: PokemonRepository) {
    operator fun invoke(): Flow<PagingData<Pokemon>> = repository.getPokemonStream()
}