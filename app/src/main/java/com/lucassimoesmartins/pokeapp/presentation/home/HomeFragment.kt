package com.lucassimoesmartins.pokeapp.presentation.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.lucassimoesmartins.pokeapp.PokeApplication
import com.lucassimoesmartins.pokeapp.R
import com.lucassimoesmartins.pokeapp.databinding.FragmentHomeBinding
import com.lucassimoesmartins.pokeapp.domain.usecase.FetchPokemonListUseCase
import com.lucassimoesmartins.pokeapp.presentation.PokemonAdapter

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels {
        val appContainer = (requireActivity().application as PokeApplication).appContainer
        HomeViewModelFactory(FetchPokemonListUseCase(appContainer.pokemonRepository))
    }

    private val adapter = PokemonAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        setupRecyclerView()
        setupObservers()

        viewModel.onAction(HomeAction.OnFetchPokemonList)
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is HomeState.Loading -> {
                    binding.recyclerView.isVisible = false
                    binding.progressBar.isVisible = true
                    binding.txtErrorMessage.isVisible = false
                }
                is HomeState.Success -> {
                    binding.recyclerView.isVisible = true
                    binding.progressBar.isVisible = false
                    binding.txtErrorMessage.isVisible = false
                    adapter.submitList(state.list)
                }
                is HomeState.Error -> {
                    binding.recyclerView.isVisible = false
                    binding.progressBar.isVisible = false
                    binding.txtErrorMessage.isVisible = true
                    binding.txtErrorMessage.text = state.message ?: getString(R.string.something_went_wrong)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}