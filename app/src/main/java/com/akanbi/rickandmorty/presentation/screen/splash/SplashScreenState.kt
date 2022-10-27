package com.akanbi.rickandmorty.presentation.screen.splash

import androidx.compose.runtime.Immutable
import com.akanbi.rickandmorty.domain.model.Character
import com.akanbi.rickandmorty.presentation.components.model.SimpleElement
import com.akanbi.rickandmorty.presentation.core.UIEvent
import com.akanbi.rickandmorty.presentation.core.UIState

@Immutable
sealed class SplashScreenUIEvent: UIEvent {
    data class OnLoading(val isLoading: Boolean): SplashScreenUIEvent()
    data class ShowCharacterListScreen(val showCharacterListScreen: Boolean): SplashScreenUIEvent()
}

@Immutable
data class SplashScreenUIState(
    val isLoading: Boolean,
    val showCharacterListScreen: Boolean,
) : UIState {

    companion object {
        fun initial() = SplashScreenUIState(
            isLoading = true,
            showCharacterListScreen = false
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, showCharacterListScreen: $showCharacterListScreen"
    }
}