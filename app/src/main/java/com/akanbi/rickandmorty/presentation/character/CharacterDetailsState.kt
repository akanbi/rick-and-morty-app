package com.akanbi.rickandmorty.presentation.character

import androidx.compose.runtime.Immutable
import com.akanbi.rickandmorty.domain.model.Character
import com.akanbi.rickandmorty.presentation.components.model.SimpleElement
import com.akanbi.rickandmorty.presentation.core.UIEvent
import com.akanbi.rickandmorty.presentation.core.UIState

@Immutable
sealed class CharacterDetailsUIEvent: UIEvent {
    data class ShowCharacterData(val characterSelected: Character) : CharacterDetailsUIEvent()
    data class OnLoading(val isLoading: Boolean): CharacterDetailsUIEvent()
    data class OnError(val isError: Boolean): CharacterDetailsUIEvent()
    data class ShowEpisodes(val characterSelected: Character, val episodes: List<SimpleElement>): CharacterDetailsUIEvent()
}

@Immutable
data class CharacterDetailsUIState(
    val isLoading: Boolean,
    val isError: Boolean,
    val characterSelected: Character?,
    val episodes: List<SimpleElement>
) : UIState {

    companion object {
        fun initial() = CharacterDetailsUIState(
            isLoading = true,
            isError = false,
            characterSelected = null,
            episodes = emptyList()
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, characterSelected: ${characterSelected}, isError: $isError"
    }
}