package com.lucassimoesmartins.pokeapp.di

import com.lucassimoesmartins.pokeapp.data.repository.PokemonRepository
import com.lucassimoesmartins.pokeapp.data.repository.PokemonRepositoryImpl
import com.lucassimoesmartins.pokeapp.data.remote.PokemonApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL ="https://pokeapi.co/api/v2/"

class AppContainer {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }

    private val pokemonApi: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }

    val pokemonRepository: PokemonRepository by lazy {
        PokemonRepositoryImpl(pokemonApi)
    }
}