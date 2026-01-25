package com.lucassimoesmartins.pokeapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lucassimoesmartins.pokeapp.domain.usecase.GetPokemonStreamUseCase

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val useCase: GetPokemonStreamUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}