package com.example.week8pokemon.data.responses


import com.google.gson.annotations.SerializedName

data class PalParkEncounter(
    val area: Area,
    @SerializedName("base_score")
    val baseScore: Int,
    val rate: Int
)