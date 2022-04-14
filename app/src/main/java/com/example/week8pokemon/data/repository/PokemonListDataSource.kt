package com.example.week8pokemon.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.week8pokemon.data.api.FIRST_PAGE
import com.example.week8pokemon.data.api.LIMIT
import com.example.week8pokemon.data.api.PokemonInterface
import com.example.week8pokemon.data.responses.PokemonDetails
import com.example.week8pokemon.data.responses.PokemonList
import com.example.week8pokemon.data.responses.Result
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PokemonListDataSource(private val apiService: PokemonInterface,
                            private val compositeDisposable: CompositeDisposable) {

//    private  var page = FIRST_PAGE
//    private var limit = LIMIT

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _downloadedPokemonListResponse = MutableLiveData<PokemonList>()
    val downloadedPokemonListResponse: LiveData<PokemonList>
        get() = _downloadedPokemonListResponse


    fun getPokemonList(limit:Int, offset:Int){
        _networkState.postValue(NetworkState.LOADING)
        try {
            compositeDisposable.add(
                apiService.getPokemonList(0,0)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadedPokemonListResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            it.message?.let { it -> Log.e("PokemonListSource", it) }
                        })
            )
        }
        catch (e: Exception){
            e.message?.let { Log.e("PokemonDetailsSource", it) }
        }
    }
}
//    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Result>
//    ) {
//        networkState.postValue(NetworkState.LOADING)
//        compositeDisposable.add(
//            apiService.getPokemonList(20,0)
//            .subscribeOn(Schedulers.io())
//            .subscribe(
//                {
//                    callback.onResult(it.results, null,it.next.toInt())
//                    networkState.postValue(NetworkState.LOADED)
//                },
//                {
//                    networkState.postValue(NetworkState.ERROR)
//                    it.message?.let { it-> Log.e("PokiemonListDataSource", it) }
//                }))
//    }
//    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
//        networkState.postValue(NetworkState.LOADING)
//        compositeDisposable.add(
//            apiService.getPokemonList(20,0)
//                .subscribeOn(Schedulers.io())
//                .subscribe(
//                    {
//                        if (it.count >= params.key){
//                            callback.onResult(it.results, params.key +1)
//                            networkState.postValue(NetworkState.LOADED)
//                        }else{
//                           networkState.postValue(NetworkState.ENDOFLIST)
//                        }
//                    },
//                    {
//                        networkState.postValue(NetworkState.ERROR)
//                        it.message?.let { it1 -> Log.e("PokiemonListDataSource", it1) }
//                    }))
//    }
//
//    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
//        TODO("Not yet implemented")
//    }

//}