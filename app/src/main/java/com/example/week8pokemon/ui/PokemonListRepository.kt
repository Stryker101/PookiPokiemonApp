package com.example.week8pokemon.ui

import androidx.lifecycle.LiveData
import com.example.week8pokemon.data.api.PokemonInterface
import com.example.week8pokemon.data.repository.NetworkState
import com.example.week8pokemon.data.repository.PokemonListDataSource
import com.example.week8pokemon.data.responses.PokemonList
import io.reactivex.disposables.CompositeDisposable

class PokemonListRepository(private val apiService: PokemonInterface) {

    private lateinit var pokemonListDataSource: PokemonListDataSource

    fun fetchLivePokemonPagedList(compositeDisposable: CompositeDisposable, limit: Int, offset: Int): LiveData<PokemonList> {
        pokemonListDataSource = PokemonListDataSource(apiService, compositeDisposable)
        pokemonListDataSource.getPokemonList(limit, offset)

        return pokemonListDataSource.downloadedPokemonListResponse
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return pokemonListDataSource.networkState
    }
}
