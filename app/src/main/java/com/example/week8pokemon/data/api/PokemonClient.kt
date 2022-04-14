package com.example.week8pokemon.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://pokeapi.co/api/v2/"
const val FIRST_PAGE = 1
const val POST_PER_PAGE = 20
const val LIMIT = 400

object PokemonClient {

    fun getClient(): PokemonInterface {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(60,TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonInterface::class.java)
    }
}