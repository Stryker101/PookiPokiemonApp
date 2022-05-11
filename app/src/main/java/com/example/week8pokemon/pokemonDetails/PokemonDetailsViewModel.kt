package com.example.week8pokemon.pokemonDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.week8pokemon.data.repository.NetworkState
import com.example.week8pokemon.data.responses.PokemonDetails
import io.reactivex.disposables.CompositeDisposable

class PokemonDetailsViewModel(
    private val pokemonRepository: PokemonDetailsRepository,
    pokemonName: String,

) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val pokemonDetails: LiveData<PokemonDetails> by lazy {
        pokemonRepository.fetchSinglePokemonDetails(compositeDisposable, pokemonName)
    }

    fun fetch(url: String) {
        pokemonRepository.fetchSpecieDetails(compositeDisposable, url)
    }

    val networkState: LiveData<NetworkState> by lazy {
        pokemonRepository.getPokemonDetailsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
