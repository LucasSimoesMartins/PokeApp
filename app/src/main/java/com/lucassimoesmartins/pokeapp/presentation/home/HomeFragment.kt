package com.lucassimoesmartins.pokeapp.presentation.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.lucassimoesmartins.pokeapp.PokeApplication
import com.lucassimoesmartins.pokeapp.R
import com.lucassimoesmartins.pokeapp.databinding.FragmentHomeBinding
import com.lucassimoesmartins.pokeapp.domain.usecase.GetPokemonStreamUseCase
import com.lucassimoesmartins.pokeapp.presentation.PokemonAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels {
        val appContainer = (requireActivity().application as PokeApplication).appContainer
        HomeViewModelFactory(GetPokemonStreamUseCase(appContainer.pokemonRepository))
    }

    private val adapter = PokemonAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        setupRecyclerView()
        setupListeners()
        setupLoadStateObserver()
        observePagingData()

    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter
    }

    private fun setupListeners() {
        binding.btnRetry.setOnClickListener { adapter.retry() }
    }

    private fun observePagingData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pokemonPagingDataFlow.collectLatest { pagingData ->
                    adapter.submitData(pagingData)
                }
            }
        }
    }

    private fun setupLoadStateObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter.loadStateFlow.collect { loadStates ->
                    val isInitialLoading = loadStates.refresh is androidx.paging.LoadState.Loading
                    val errorState = loadStates.refresh as? androidx.paging.LoadState.Error

                    binding.progressBar.isVisible = isInitialLoading
                    binding.recyclerView.isVisible = !isInitialLoading && errorState == null
                    binding.errorContainer.isVisible = errorState != null
                    
                    errorState?.let {
                        binding.txtErrorMessage.text = it.error.localizedMessage ?: getString(R.string.something_went_wrong)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}