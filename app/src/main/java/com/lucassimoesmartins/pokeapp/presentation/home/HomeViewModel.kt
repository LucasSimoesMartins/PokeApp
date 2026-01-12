package com.lucassimoesmartins.pokeapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucassimoesmartins.pokeapp.domain.model.OperationResult
import com.lucassimoesmartins.pokeapp.domain.usecase.FetchPokemonListUseCase

class HomeViewModel(private val fetchPokemonListUseCase: FetchPokemonListUseCase) : ViewModel() {

    private val _state: MutableLiveData<HomeState> = MutableLiveData()
    val state: LiveData<HomeState> = _state

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.OnFetchPokemonList -> onFetchPokemonList()
        }
    }

    private fun onFetchPokemonList() {
        _state.value = HomeState.Loading

        fetchPokemonListUseCase { result ->
            when (result) {
                is OperationResult.Success -> {
                    _state.postValue(HomeState.Success(result.data))
                }
                is OperationResult.Error -> {
                    _state.postValue(HomeState.Error(result.message))
                }
            }
        }
    }
}