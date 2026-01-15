package com.lucassimoesmartins.pokeapp.data

import com.lucassimoesmartins.pokeapp.data.remote.PokemonApiService
import com.lucassimoesmartins.pokeapp.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonRepositoryImpl(private val api: PokemonApiService) : PokemonRepository {

    override fun fetchPokemonList(): Flow<List<Pokemon>> = flow {
        val pokemonList = api.getPokemonList().toDomain()
        emit(pokemonList)
    }
}