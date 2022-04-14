package com.example.week8pokemon.data.responses


import com.google.gson.annotations.SerializedName

data class FlavorTextEntry(
    @SerializedName("flavor_text")
    val flavorText: String,
    val language: Language,
    val version: VersionXX
)