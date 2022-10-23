package com.akanbi.rickandmorty.network.model.episode

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)