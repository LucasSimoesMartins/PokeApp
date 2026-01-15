package com.lucassimoesmartins.pokeapp.data.remote

import retrofit2.http.GET

interface PokemonApiService {
    @GET("pokemon")
    suspend fun getPokemonList(): PokemonResponse
}