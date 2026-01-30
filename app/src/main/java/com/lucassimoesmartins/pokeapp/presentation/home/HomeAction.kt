package com.lucassimoesmartins.pokeapp.presentation.home

import com.lucassimoesmartins.pokeapp.domain.model.Pokemon

sealed interface HomeAction {
    data class OnFavoriteClick(val pokemon: Pokemon) : HomeAction
}