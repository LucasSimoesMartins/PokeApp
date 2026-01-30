package com.lucassimoesmartins.pokeapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lucassimoesmartins.pokeapp.data.local.PokemonDao
import com.lucassimoesmartins.pokeapp.data.local.PokemonEntity
import com.lucassimoesmartins.pokeapp.data.paging.PokemonPagingSource
import com.lucassimoesmartins.pokeapp.data.remote.PokemonApiService
import com.lucassimoesmartins.pokeapp.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PokemonRepositoryImpl(
    private val api: PokemonApiService,
    private val dao: PokemonDao
) : PokemonRepository {

    override fun getPokemonStream(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PokemonPagingSource(api) }
        ).flow
    }

    override fun getFavoritesStream(): Flow<List<Pokemon>> {
        return dao.getAllFavorites().map { entities ->
            entities.map { entity ->
                Pokemon(
                    name = entity.name,
                    imageUrl = entity.imageUrl,
                    isFavorite = true
                )
            }
        }
    }

    override suspend fun saveFavorite(pokemon: Pokemon) {
        dao.insertFavorite(PokemonEntity(pokemon.name, pokemon.imageUrl))
    }

    override suspend fun removeFavorite(pokemon: Pokemon) {
        dao.deleteFavorite(PokemonEntity(pokemon.name, pokemon.imageUrl))
    }
}