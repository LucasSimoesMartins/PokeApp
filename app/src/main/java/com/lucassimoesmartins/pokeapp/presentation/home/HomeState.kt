package com.lucassimoesmartins.pokeapp.presentation.home

import com.lucassimoesmartins.pokeapp.domain.model.Pokemon

sealed interface HomeState {
    object Loading : HomeState
    data class Success(val list: List<Pokemon>) : HomeState
    data class Error(val message: String? = null) : HomeState
}