package com.lucassimoesmartins.pokeapp.presentation.favorites

import com.lucassimoesmartins.pokeapp.domain.model.Pokemon

interface FavoritesAction {
    data class OnRemoveFavorite(val pokemon: Pokemon) : FavoritesAction
}