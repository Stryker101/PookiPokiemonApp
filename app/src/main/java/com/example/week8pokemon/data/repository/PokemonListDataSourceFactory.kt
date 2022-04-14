package com.example.week8pokemon.data.repository

//import androidx.lifecycle.MutableLiveData
//import androidx.paging.DataSource
//import com.example.week8pokemon.data.api.PokemonInterface
//import com.example.week8pokemon.data.responses.PokemonList
//import com.example.week8pokemon.data.responses.Result
//import io.reactivex.disposables.CompositeDisposable
//
//class PokemonListDataSourceFactory(private val apiService: PokemonInterface,
//                                   private val compositeDisposable: CompositeDisposable
//): DataSource.Factory<Int, Result>() {
//
//    val pokemonLiveDataSource = MutableLiveData<PokemonListDataSource>()
//
//    override fun create(): DataSource<Int, Result> {
//        val pokemonListDataSource = PokemonListDataSource(apiService, compositeDisposable)
//        pokemonLiveDataSource.postValue(pokemonListDataSource)
//        return pokemonListDataSource
//    }
//}