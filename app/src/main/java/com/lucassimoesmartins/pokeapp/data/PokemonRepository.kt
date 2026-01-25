package com.lucassimoesmartins.pokeapp.data

import androidx.paging.PagingData
import com.lucassimoesmartins.pokeapp.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonStream(): Flow<PagingData<Pokemon>>
}