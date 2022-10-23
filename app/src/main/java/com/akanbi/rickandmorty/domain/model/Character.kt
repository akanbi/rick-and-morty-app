package com.akanbi.rickandmorty.domain.model

import android.os.Parcelable
import com.akanbi.rickandmorty.network.model.character.Info
import com.akanbi.rickandmorty.presentation.components.model.SimpleElement
import kotlinx.android.parcel.Parcelize

class CharacterUI(
    val characterList: List<Character> = listOf(),
    val pagination: Info = Info()
)

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val gender: String,
    val image: String,
    val url: String,
    val species: String,
    val location: String,
    val status: String,
    val episodeIds: List<String>
) : Parcelable