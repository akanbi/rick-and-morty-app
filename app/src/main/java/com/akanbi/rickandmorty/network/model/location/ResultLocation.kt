package com.akanbi.rickandmorty.network.model.location

data class ResultLocation(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)