package com.lucassimoesmartins.pokeapp.di

import android.content.Context
import androidx.room.Room
import com.lucassimoesmartins.pokeapp.data.local.AppDatabase
import com.lucassimoesmartins.pokeapp.data.remote.PokemonApiService
import com.lucassimoesmartins.pokeapp.data.repository.PokemonRepository
import com.lucassimoesmartins.pokeapp.data.repository.PokemonRepositoryImpl
import com.lucassimoesmartins.pokeapp.domain.usecase.GetFavoritesStreamUseCase
import com.lucassimoesmartins.pokeapp.domain.usecase.GetPokemonStreamUseCase
import com.lucassimoesmartins.pokeapp.domain.usecase.RemoveFavoriteUseCase
import com.lucassimoesmartins.pokeapp.domain.usecase.SaveFavoriteUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL ="https://pokeapi.co/api/v2/"

class AppContainer(private val context: Context) {

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
        PokemonRepositoryImpl(
            api = pokemonApi,
            dao = database.pokemonDao()
        )
    }

    private val database: AppDatabase by lazy {
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "pokemon_database"
        ).build()
    }

    val getPokemonStreamUseCase: GetPokemonStreamUseCase by lazy {
        GetPokemonStreamUseCase(pokemonRepository)
    }

    val saveFavoriteUseCase: SaveFavoriteUseCase by lazy {
        SaveFavoriteUseCase(pokemonRepository)
    }

    val removeFavoriteUseCase: RemoveFavoriteUseCase by lazy {
        RemoveFavoriteUseCase(pokemonRepository)
    }

    val getFavoritesStreamUseCase: GetFavoritesStreamUseCase by lazy {
        GetFavoritesStreamUseCase(pokemonRepository)
    }
}