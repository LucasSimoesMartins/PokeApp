package com.lucassimoesmartins.pokeapp.data.repository

import androidx.paging.PagingData
import com.lucassimoesmartins.pokeapp.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonStream(): Flow<PagingData<Pokemon>>

    fun getFavoritesStream() : Flow<List<Pokemon>>

    suspend fun saveFavorite(pokemon: Pokemon)

    suspend fun removeFavorite(pokemon: Pokemon)
}