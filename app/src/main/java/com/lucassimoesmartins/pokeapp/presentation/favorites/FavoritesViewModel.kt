package com.lucassimoesmartins.pokeapp.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucassimoesmartins.pokeapp.domain.model.Pokemon
import com.lucassimoesmartins.pokeapp.domain.usecase.GetFavoritesStreamUseCase
import com.lucassimoesmartins.pokeapp.domain.usecase.RemoveFavoriteUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(
    getFavoritesStreamUseCase: GetFavoritesStreamUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
) : ViewModel() {

    val favoritesFlow: StateFlow<List<Pokemon>> = getFavoritesStreamUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun onAction(action: FavoritesAction) {
        when (action) {
            is FavoritesAction.OnRemoveFavorite -> removeFavorite(action.pokemon)
        }
    }

    private fun removeFavorite(pokemon: Pokemon) {
        viewModelScope.launch {
            removeFavoriteUseCase(pokemon)
        }
    }

}