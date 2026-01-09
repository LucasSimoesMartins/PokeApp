package com.lucassimoesmartins.pokeapp.data.remote

import retrofit2.Call
import retrofit2.http.GET

interface PokemonApiService {
    @GET("pokemon")
    fun getPokemonList(): Call<PokemonResponse>
}