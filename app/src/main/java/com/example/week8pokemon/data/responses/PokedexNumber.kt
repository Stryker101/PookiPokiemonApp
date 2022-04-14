package com.example.week8pokemon.data.responses


import com.google.gson.annotations.SerializedName

data class PokedexNumber(
    @SerializedName("entry_number")
    val entryNumber: Int,
    val pokedex: Pokedex
)