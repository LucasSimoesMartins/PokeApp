package com.lucassimoesmartins.pokeapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.lucassimoesmartins.pokeapp.domain.model.Pokemon
import com.lucassimoesmartins.pokeapp.domain.usecase.GetFavoritesStreamUseCase
import com.lucassimoesmartins.pokeapp.domain.usecase.GetPokemonStreamUseCase
import com.lucassimoesmartins.pokeapp.domain.usecase.RemoveFavoriteUseCase
import com.lucassimoesmartins.pokeapp.domain.usecase.SaveFavoriteUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class HomeViewModel(
    getPokemonStreamUseCase: GetPokemonStreamUseCase,
    getFavoritesStreamUseCase: GetFavoritesStreamUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
) : ViewModel() {

    val pokemonPagingDataFlow: Flow<PagingData<Pokemon>> = combine(
        getPokemonStreamUseCase().cachedIn(viewModelScope),
        getFavoritesStreamUseCase()
    ) { pagingData, favorites ->
        val favoriteNames = favorites.map { it.name }.toSet()
        pagingData.map { pokemon ->
            pokemon.copy(isFavorite = favoriteNames.contains(pokemon.name))
        }
    }.cachedIn(viewModelScope)

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.OnFavoriteClick -> toggleFavorite(action.pokemon)
        }
    }

    private fun toggleFavorite(pokemon: Pokemon) {
        viewModelScope.launch {
            if (pokemon.isFavorite) {
                removeFavoriteUseCase(pokemon)
            } else {
                saveFavoriteUseCase(pokemon)
            }
        }
    }
}