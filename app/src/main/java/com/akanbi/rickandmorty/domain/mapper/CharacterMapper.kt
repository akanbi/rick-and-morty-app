package com.akanbi.rickandmorty.domain.mapper

import com.akanbi.rickandmorty.domain.model.Character
import com.akanbi.rickandmorty.network.model.character.Result

class CharacterMapper : GeneralMapper<Result, Character> {

    override fun convert(model: Result) =
        Character(
            id = model.id,
            name = model.name,
            gender = model.gender,
            image = model.image,
            url = model.url,
            species = model.species,
            location = model.location.name,
            status = model.status,
            episodeIds = episodeIdsList(model.episode)
        )

    private fun episodeIdsList(episodes: List<String>): List<String> {
        val ids = mutableListOf<String>()
        episodes.forEach {
            ids.add(getIdByUrl(it))
        }
        return ids
    }

    private fun getIdByUrl(url: String) = url.split("/").last()

}