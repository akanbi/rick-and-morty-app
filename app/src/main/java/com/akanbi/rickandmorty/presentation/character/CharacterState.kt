package com.akanbi.rickandmorty.presentation.character

import androidx.compose.runtime.Immutable
import com.akanbi.rickandmorty.domain.model.Character
import com.akanbi.rickandmorty.presentation.core.UIEvent
import com.akanbi.rickandmorty.presentation.core.UIState

@Immutable
sealed class CharacterUIEvent: UIEvent {
    data class OnLoading(val isLoading: Boolean): CharacterUIEvent()
    data class OnError(val isError: Boolean): CharacterUIEvent()
    data class ShowCharacters(
        val characters: MutableList<Character>,
        val isLoading: Boolean,
        val isRefreshing: Boolean,
        val isPagination: Boolean
    ): CharacterUIEvent()
    data class OnPagination(
        val characters: MutableList<Character>,
        val isPagination: Boolean,
        val isLoading: Boolean,
        val isRefreshing: Boolean,
    ): CharacterUIEvent()
    data class OnRefresh(
        val characters: MutableList<Character>,
        val isPagination: Boolean,
        val isLoading: Boolean,
        val isRefreshing: Boolean
    ): CharacterUIEvent()
}

@Immutable
data class CharacterUIState(
    val isLoading: Boolean,
    val isError: Boolean,
    val characters: MutableList<Character> = mutableListOf(),
    val isRefreshing: Boolean,
    val isPagination: Boolean
) : UIState {

    companion object {
        fun initial() = CharacterUIState(
            isLoading = false,
            isError = false,
            characters = mutableListOf(),
            isRefreshing = false,
            isPagination = false
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, characters.size: ${characters.size}, isError: $isError, isRefreshing: $isRefreshing"
    }
}