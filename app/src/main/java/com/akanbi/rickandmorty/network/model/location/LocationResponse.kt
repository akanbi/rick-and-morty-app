package com.akanbi.rickandmorty.network.model.location

data class LocationResponse(
    val info: Info,
    val results: List<ResultLocation>
)