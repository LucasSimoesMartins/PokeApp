package com.lucassimoesmartins.pokeapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lucassimoesmartins.pokeapp.domain.model.Pokemon
import com.lucassimoesmartins.pokeapp.domain.usecase.GetPokemonStreamUseCase
import kotlinx.coroutines.flow.Flow

class HomeViewModel(getPokemonStreamUseCase: GetPokemonStreamUseCase) : ViewModel() {

    val pokemonPagingDataFlow: Flow<PagingData<Pokemon>> = getPokemonStreamUseCase().cachedIn(viewModelScope)

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.OnFavoriteClick -> {

            }
        }
    }
}