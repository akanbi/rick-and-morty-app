package com.akanbi.rickandmorty.domain.mapper

import com.akanbi.rickandmorty.domain.model.Character
import com.akanbi.rickandmorty.network.model.CharacterResponse

class CharacterMapper : GeneralMapper<CharacterResponse, Character> {

    override fun convert(model: CharacterResponse) =
        Character(

        )

}