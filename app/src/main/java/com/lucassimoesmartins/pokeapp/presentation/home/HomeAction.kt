package com.lucassimoesmartins.pokeapp.presentation.home

sealed interface HomeAction {
    data object OnFavoriteClick : HomeAction
}