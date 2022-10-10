package com.akanbi.rickandmorty.domain.mapper

import com.akanbi.rickandmorty.domain.model.Character
import com.akanbi.rickandmorty.network.model.CharacterResponse
import com.akanbi.rickandmorty.network.model.Result
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

class CharacterMapper : GeneralMapper<Result, Character> {

    override fun convert(model: Result) =
        Character(
            id = model.id,
            name = model.name,
            gender = model.gender,
            image = model.image,
            url = model.url,
            species = model.species,
            location = model.location.name
        )

}