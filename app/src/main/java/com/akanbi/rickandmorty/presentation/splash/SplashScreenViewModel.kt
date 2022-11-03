package com.akanbi.rickandmorty.presentation.splash

import androidx.lifecycle.viewModelScope
import com.akanbi.rickandmorty.common.ProviderContext
import com.akanbi.rickandmorty.presentation.core.CoreViewModel
import com.akanbi.rickandmorty.presentation.core.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val providerContext: ProviderContext
) : CoreViewModel<SplashScreenUIState, SplashScreenUIEvent>() {
    private val reducer = SplashScreenReducer(SplashScreenUIState.initial())
    override val state: StateFlow<SplashScreenUIState>
        get() = reducer.state

    init {
        viewModelScope.launch(providerContext.main) {
            delay(3000)
            sendEvent(SplashScreenUIEvent.ShowCharacterListScreen(showCharacterListScreen = true))
        }
    }

    fun sendEvent(event: SplashScreenUIEvent) {
        reducer.sendEvent(event)
    }

    private class SplashScreenReducer(initialState: SplashScreenUIState) :
        Reducer<SplashScreenUIState, SplashScreenUIEvent>(initialState) {
        override fun reduce(oldState: SplashScreenUIState, event: SplashScreenUIEvent) {
            when(event) {
                is SplashScreenUIEvent.OnLoading -> {
                    setState(oldState.copy(
                        isLoading = event.isLoading,
                        showCharacterListScreen = false
                    ))
                }
                is SplashScreenUIEvent.ShowCharacterListScreen -> {
                    setState(oldState.copy(
                        isLoading = false,
                        showCharacterListScreen = event.showCharacterListScreen
                    ))
                }
            }
        }
    }
}