package com.lucassimoesmartins.pokeapp.data

import com.lucassimoesmartins.pokeapp.data.remote.PokemonApiService
import com.lucassimoesmartins.pokeapp.data.remote.PokemonResponse
import com.lucassimoesmartins.pokeapp.domain.model.OperationResult
import com.lucassimoesmartins.pokeapp.domain.model.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepositoryImpl(private val api: PokemonApiService) : PokemonRepository {

    override fun fetchPokemonList(onResult: (OperationResult<List<Pokemon>>) -> Unit) {
        api.getPokemonList().enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                if (response.isSuccessful) {
                    val pokemonList = response.body()?.toDomain() ?: emptyList()
                    onResult(OperationResult.Success(pokemonList))
                } else {
                    onResult(OperationResult.Error(response.message()))
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                onResult(OperationResult.Error(t.message, t))
            }
        })
    }
}