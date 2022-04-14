package com.example.week8pokemon.data.responses


import com.google.gson.annotations.SerializedName

data class Variety(
    @SerializedName("is_default")
    val isDefault: Boolean,
    val pokemon: Pokemon
)