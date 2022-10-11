package com.akanbi.rickandmorty.domain.model

import com.akanbi.rickandmorty.network.model.Info

class CharacterUI(
    val characterList: List<Character> = listOf(),
    val pagination: Info = Info()
)

class Character(
    val id: Int,
    val name: String,
    val gender: String,
    val image: String,
    val url: String,
    val species: String,
    val location: String,
    val status: String
)