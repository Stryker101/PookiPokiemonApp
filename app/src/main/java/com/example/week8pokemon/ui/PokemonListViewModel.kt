package com.example.week8pokemon.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.week8pokemon.data.repository.NetworkState
import com.example.week8pokemon.data.responses.PokemonList
import io.reactivex.disposables.CompositeDisposable

class PokemonListViewModel(private val pokemonListRepository: PokemonListRepository, limit: Int, offset: Int) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val pokemonList: LiveData<PokemonList> by lazy {
        pokemonListRepository.fetchLivePokemonPagedList(compositeDisposable, limit, offset)
    }
    val networkState: LiveData<NetworkState> by lazy {
        pokemonListRepository.getNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
