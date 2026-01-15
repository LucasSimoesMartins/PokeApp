package com.lucassimoesmartins.pokeapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucassimoesmartins.pokeapp.domain.usecase.FetchPokemonListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeViewModel(private val fetchPokemonListUseCase: FetchPokemonListUseCase) : ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.OnFetchPokemonList -> onFetchPokemonList()
        }
    }

    private fun onFetchPokemonList() {
        fetchPokemonListUseCase().onEach { pokemonList ->
                _state.value = HomeState.Success(pokemonList)
            }.catch { exception ->
                _state.value = HomeState.Error(exception.message)
            }.launchIn(viewModelScope)
    }
}