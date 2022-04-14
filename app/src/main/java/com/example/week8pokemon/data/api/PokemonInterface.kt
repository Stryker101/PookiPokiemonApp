package com.example.week8pokemon.data.api

import com.example.week8pokemon.data.responses.ForSpecies
import com.example.week8pokemon.data.responses.PokemonDetails
import com.example.week8pokemon.data.responses.PokemonList
import com.example.week8pokemon.data.responses.Species
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonInterface {

    @GET("pokemon/{Name}")
    fun getPokemonDetails(
        @Path("Name") name: String
    ): Single<PokemonDetails>


    @GET("pokemon-species/{url}")
    fun getSpecieDetails(
        @Path("url") url: String
    ): Single<ForSpecies>

    @GET("pokemon")
    fun getPokemonList(
        @Query("limit") limit:Int,
        @Query("offset") offset:Int
    ): Single<PokemonList>

}