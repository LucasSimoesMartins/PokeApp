package com.lucassimoesmartins.pokeapp.presentation.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.lucassimoesmartins.pokeapp.PokeApplication
import com.lucassimoesmartins.pokeapp.R
import com.lucassimoesmartins.pokeapp.databinding.FragmentFavoritesBinding
import com.lucassimoesmartins.pokeapp.presentation.common.PokemonAdapter
import kotlinx.coroutines.launch

class FavoritesFragment: Fragment(R.layout.fragment_favorites) {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoritesViewModel by viewModels {
        val appContainer = (requireActivity().application as PokeApplication).appContainer
        FavoritesViewModelFactory(
            getFavoritesStreamUseCase = appContainer.getFavoritesStreamUseCase,
            removeFavoriteUseCase = appContainer.removeFavoriteUseCase
        )
    }

    private val adapter = PokemonAdapter { pokemon ->
        viewModel.onAction(FavoritesAction.OnRemoveFavorite(pokemon))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoritesBinding.bind(view)

        setupRecyclerView()
        observeFavorites()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter
    }

    private fun observeFavorites() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favoritesFlow.collect { favorites ->
                    adapter.submitData(androidx.paging.PagingData.from(favorites))
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}