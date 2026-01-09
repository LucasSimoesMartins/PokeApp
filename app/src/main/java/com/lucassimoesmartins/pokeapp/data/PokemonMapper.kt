package com.lucassimoesmartins.pokeapp.data

import com.lucassimoesmartins.pokeapp.data.remote.PokemonResponse
import com.lucassimoesmartins.pokeapp.domain.model.Pokemon

fun PokemonResponse.toDomain(): List<Pokemon> {

    return this.results.map { dto ->
        val id = dto.detailUrl.split("/").dropLast(1).last()

        Pokemon(
            name = dto.name.replaceFirstChar { it.uppercase() },
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
        )
    }
}