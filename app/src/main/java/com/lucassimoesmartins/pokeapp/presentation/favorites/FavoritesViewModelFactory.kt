package com.lucassimoesmartins.pokeapp.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lucassimoesmartins.pokeapp.domain.usecase.GetFavoritesStreamUseCase
import com.lucassimoesmartins.pokeapp.domain.usecase.RemoveFavoriteUseCase

@Suppress("UNCHECKED_CAST")
class FavoritesViewModelFactory(
    private val getFavoritesStreamUseCase: GetFavoritesStreamUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            return FavoritesViewModel(getFavoritesStreamUseCase, removeFavoriteUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}