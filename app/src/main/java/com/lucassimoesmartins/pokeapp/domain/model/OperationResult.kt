package com.lucassimoesmartins.pokeapp.domain.model

sealed interface OperationResult <out T> {
    data class Success<out T>(val data: T) : OperationResult<T>
    data class Error(val message: String?, val cause: Throwable? = null) : OperationResult<Nothing>
}