package com.example.week8pokemon.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.week8pokemon.data.api.PokemonInterface
import com.example.week8pokemon.data.responses.PokemonList
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PokemonListDataSource(
    private val apiService: PokemonInterface,
    private val compositeDisposable: CompositeDisposable
) {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _downloadedPokemonListResponse = MutableLiveData<PokemonList>()
    val downloadedPokemonListResponse: LiveData<PokemonList>
        get() = _downloadedPokemonListResponse

    fun getPokemonList(limit: Int, offset: Int) {
        _networkState.postValue(NetworkState.LOADING)
        try {
            compositeDisposable.add(
                apiService.getPokemonList(0, 0)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadedPokemonListResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            it.message?.let { it -> Log.e("PokemonListSource", it) }
                        }
                    )
            )
        } catch (e: Exception) {
            e.message?.let { Log.e("PokemonDetailsSource", it) }
        }
    }
}

