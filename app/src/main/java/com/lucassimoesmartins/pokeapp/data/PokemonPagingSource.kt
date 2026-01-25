package com.lucassimoesmartins.pokeapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lucassimoesmartins.pokeapp.data.remote.PokemonApiService
import com.lucassimoesmartins.pokeapp.domain.model.Pokemon
import retrofit2.HttpException

class PokemonPagingSource(private val api: PokemonApiService) : PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val currentOffset = params.key ?: 0

            val response = api.getPokemonList(
                offset = currentOffset,
                limit = params.loadSize
            )

            if (response.isSuccessful) {
                val pokemonResponse = response.body()
                val pokemonList: List<Pokemon> = pokemonResponse?.toDomain() ?: emptyList()

                val nextKey = if (pokemonResponse?.next == null) null else currentOffset + params.loadSize
                val prevKey = if (currentOffset == 0) null else currentOffset - params.loadSize

                LoadResult.Page(data = pokemonList, prevKey = prevKey, nextKey = nextKey)
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(state.config.pageSize) ?: anchorPage?.nextKey?.minus(state.config.pageSize)
        }
    }
}