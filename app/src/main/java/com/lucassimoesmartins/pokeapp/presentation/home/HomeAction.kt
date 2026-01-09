package com.lucassimoesmartins.pokeapp.presentation.home

sealed interface HomeAction {
    data object OnFetchPokemonList : HomeAction
}