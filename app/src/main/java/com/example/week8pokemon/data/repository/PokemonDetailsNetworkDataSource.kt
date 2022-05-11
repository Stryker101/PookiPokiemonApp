package com.example.week8pokemon.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.week8pokemon.data.api.PokemonInterface
import com.example.week8pokemon.data.responses.ForSpecies
import com.example.week8pokemon.data.responses.PokemonDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PokemonDetailsNetworkDataSource(
    private val apiService: PokemonInterface,
    private val compositeDisposable: CompositeDisposable
) {
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _downloadedPokemonDetailsResponse = MutableLiveData<PokemonDetails>()
    val downloadedPokemonDetailsResponse: LiveData<PokemonDetails>
        get() = _downloadedPokemonDetailsResponse

    private val _downloadedSpeciesResponse = MutableLiveData<ForSpecies>()
    val downloadedSpeciesResponse: LiveData<ForSpecies>
        get() = _downloadedSpeciesResponse

    fun getPokemonInfo(pokemonName: String) {
        _networkState.postValue(NetworkState.LOADING)
        try {
            compositeDisposable.add(
                apiService.getPokemonDetails(pokemonName)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadedPokemonDetailsResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            it.message?.let { it -> Log.e("PokemonDetailsSource", it) }
                        }
                    )
            )
        } catch (e: Exception) {
            e.message?.let { Log.e("PokemonDetailsSource", it) }
        }
    }

    fun getSpecieInfo(url: String) {
        _networkState.postValue(NetworkState.LOADING)
        try {
            compositeDisposable.add(
                apiService.getSpecieDetails(url)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadedSpeciesResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            it.message?.let { it -> Log.e("PokemonSpecieSource", it) }
                        }
                    )
            )
        } catch (e: Exception) {
            e.message?.let { Log.e("PokemonSpecieSource", it) }
        }
    }
}
