package com.example.week8pokemon.pokemonDetails

import androidx.lifecycle.LiveData
import com.example.week8pokemon.data.api.PokemonInterface
import com.example.week8pokemon.data.repository.NetworkState
import com.example.week8pokemon.data.repository.PokemonDetailsNetworkDataSource
import com.example.week8pokemon.data.responses.ForSpecies
import com.example.week8pokemon.data.responses.PokemonDetails
import io.reactivex.disposables.CompositeDisposable

class PokemonDetailsRepository(private val apiService: PokemonInterface) {

    private lateinit var pokemonDetailsNetworkDataSource: PokemonDetailsNetworkDataSource

    fun fetchSinglePokemonDetails(compositeDisposable: CompositeDisposable, pokemonName: String): LiveData<PokemonDetails> {
        pokemonDetailsNetworkDataSource = PokemonDetailsNetworkDataSource(apiService, compositeDisposable)
        pokemonDetailsNetworkDataSource.getPokemonInfo(pokemonName)

        return pokemonDetailsNetworkDataSource.downloadedPokemonDetailsResponse
    }

    fun fetchSpecieDetails(compositeDisposable: CompositeDisposable, url: String): LiveData<ForSpecies> {
        pokemonDetailsNetworkDataSource = PokemonDetailsNetworkDataSource(apiService, compositeDisposable)
        pokemonDetailsNetworkDataSource.getPokemonInfo(url)

        return pokemonDetailsNetworkDataSource.downloadedSpeciesResponse
    }

    fun getPokemonDetailsNetworkState(): LiveData<NetworkState> {
        return pokemonDetailsNetworkDataSource.networkState
    }
}
