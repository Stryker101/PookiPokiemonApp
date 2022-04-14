package com.example.week8pokemon.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.week8pokemon.data.repository.NetworkState
import com.example.week8pokemon.data.responses.PokemonList
import com.example.week8pokemon.data.responses.Result
import io.reactivex.disposables.CompositeDisposable

class PokemonListViewModel(private val pokemonListRepository: PokemonListRepository, limit: Int, offset:Int): ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val pokemonList : LiveData<PokemonList> by lazy {
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
//class PokemonListViewModel(private val pokemonListRepository: PokemonListRepository): ViewModel() {
//    private val compositeDisposable = CompositeDisposable()
//
//    val pokemonList : LiveData<PokemonList> by lazy {
//        pokemonListRepository.fetchLivePokemonPagedList(compositeDisposable)
//    }
//    val networkState: LiveData<NetworkState> by lazy {
//        pokemonListRepository.getNetworkState()
//    }
//    fun listIsEmpty(): Boolean{
//        return pokemonList.value?.isEmpty()?: true
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        compositeDisposable.dispose()
//    }
//}