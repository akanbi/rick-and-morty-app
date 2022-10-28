package com.akanbi.rickandmorty.presentation.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.akanbi.rickandmorty.common.ProviderContext
import com.akanbi.rickandmorty.domain.GetListEpisodeByCharacterUseCase
import com.akanbi.rickandmorty.domain.core.ParametersDTO
import com.akanbi.rickandmorty.domain.model.Character
import com.akanbi.rickandmorty.presentation.core.CoreViewModel
import com.akanbi.rickandmorty.presentation.core.Reducer
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailsViewModel @AssistedInject constructor(
    private val useCase: GetListEpisodeByCharacterUseCase,
    private val providerContext: ProviderContext,
    @Assisted private val character: Character
) : CoreViewModel<CharacterDetailsUIState, CharacterDetailsUIEvent>() {

    private val reducer = CharacterDetailsReducer(CharacterDetailsUIState.initial())
    override val state: StateFlow<CharacterDetailsUIState>
        get() = reducer.state

    init {
        viewModelScope.launch {
            listEpisodesBy(character)
        }
    }

    private suspend fun listEpisodesBy(characterSelected: Character) {
            useCase.execute(
                parameters = ParametersDTO {
                    add("ids", characterSelected.episodeIds)
                },
                onSuccess = {
                    sendEvent(CharacterDetailsUIEvent.ShowEpisodes(
                        episodes = it,
                        characterSelected = characterSelected
                    ))
                },
                onError = {
                    sendEvent(CharacterDetailsUIEvent.OnError(isError = true))
                }
            )
    }

    private class CharacterDetailsReducer(initialState: CharacterDetailsUIState) :
        Reducer<CharacterDetailsUIState, CharacterDetailsUIEvent>(initialState) {
        override fun reduce(oldState: CharacterDetailsUIState, event: CharacterDetailsUIEvent) {
            when (event) {
                is CharacterDetailsUIEvent.OnLoading -> {
                    setState(oldState.copy(
                        isLoading = event.isLoading,
                        isError = false
                    ))
                }
                is CharacterDetailsUIEvent.OnError -> {
                    setState(oldState.copy(
                        isLoading = false,
                        isError = event.isError
                    ))
                }
                is CharacterDetailsUIEvent.ShowCharacterData -> {
                    setState(oldState.copy(
                        isLoading = false,
                        isError = false,
                        characterSelected = event.characterSelected
                    ))
                }
                is CharacterDetailsUIEvent.ShowEpisodes -> {
                    setState(oldState.copy(
                        isLoading = false,
                        isError = false,
                        episodes = event.episodes,
                        characterSelected = event.characterSelected
                    ))
                }
            }
        }

    }

    private fun sendEvent(event: CharacterDetailsUIEvent) {
        reducer.sendEvent(event)
    }

    @AssistedFactory
    interface Factory {
        fun create(characterSelected: Character): CharacterDetailsViewModel
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            assistedFactory: Factory, characterSelected: Character
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(characterSelected) as T
            }
        }
    }
}