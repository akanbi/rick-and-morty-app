package com.akanbi.rickandmorty.domain.mapper

import com.akanbi.rickandmorty.common.idsToListByUrl
import com.akanbi.rickandmorty.domain.model.Character
import com.akanbi.rickandmorty.network.model.character.ResultCharacter

class CharacterMapper : GeneralMapper<ResultCharacter, Character> {

    override fun convert(model: ResultCharacter) =
        Character(
            id = model.id,
            name = model.name,
            gender = model.gender,
            image = model.image,
            url = model.url,
            species = model.species,
            location = model.location.name,
            status = model.status,
            episodeIds = idsToListByUrl(model.episode)
        )
}