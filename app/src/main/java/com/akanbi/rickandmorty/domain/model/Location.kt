package com.akanbi.rickandmorty.domain.model

import com.akanbi.rickandmorty.network.model.location.Info


data class LocationModel(
    val id: Int,
    val name: String,
    val dimension: String,
    val residents: List<String>,
    val type: String,
    val url: String,
    val created: String
)

data class LocationUI(
    val locationList: List<LocationModel> = listOf(),
    val pagination: Info = Info()
)