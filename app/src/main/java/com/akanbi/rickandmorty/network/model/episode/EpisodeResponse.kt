package com.akanbi.rickandmorty.network.model.episode

data class EpisodeResponse(
    val info: Info,
    val results: List<Result>
)