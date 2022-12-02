package com.akanbi.rickandmorty.presentation.location

import androidx.lifecycle.viewModelScope
import com.akanbi.rickandmorty.common.ProviderContext
import com.akanbi.rickandmorty.domain.GetListLocationUseCase
import com.akanbi.rickandmorty.domain.GetResidentsOnLocationByIdsUseCase
import com.akanbi.rickandmorty.domain.core.ParametersDTO
import com.akanbi.rickandmorty.presentation.character.CharacterDetailsUIEvent
import com.akanbi.rickandmorty.presentation.core.CoreViewModel
import com.akanbi.rickandmorty.presentation.core.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val getListLocationUseCase: GetListLocationUseCase,
    private val getResidentsOnLocationByIdsUseCase: GetResidentsOnLocationByIdsUseCase,
    private val providerContext: ProviderContext
) : CoreViewModel<LocationUIState, LocationUIEvent>() {
    private val reducer = LocationReducer(LocationUIState.initial())
    override val state: StateFlow<LocationUIState>
        get() = reducer.state

    init {
        viewModelScope.launch(providerContext.main) {
            list()
        }
    }

    private suspend fun list() {
        getListLocationUseCase.execute(
            onSuccess = {
                sendEvent(LocationUIEvent.ShowLocations(it.locationList))
            },
            onError = {
                sendEvent(LocationUIEvent.OnError(true))
            }
        )
    }

    suspend fun fetchResidentsByLocation(ids: List<String>) {
        getResidentsOnLocationByIdsUseCase.execute(
            parameters = ParametersDTO { add("ids", ids) },
            onSuccess = {
                sendEvent(LocationUIEvent.ShowResidents(it))
            },
            onError = {
                sendEvent(LocationUIEvent.OnError(true))
            }
        )
    }

    private fun sendEvent(event: LocationUIEvent) {
        reducer.sendEvent(event)
    }

    private class LocationReducer(initialState: LocationUIState) :
        Reducer<LocationUIState, LocationUIEvent>(initialState) {
        override fun reduce(oldState: LocationUIState, event: LocationUIEvent) {
            when (event) {
                is LocationUIEvent.OnLoading -> {
                    setState(
                        oldState.copy(
                            isLoading = event.isLoading,
                            isError = false
                        )
                    )
                }
                is LocationUIEvent.OnError -> {
                    setState(
                        oldState.copy(
                            isLoading = false,
                            isError = event.isError
                        )
                    )
                }
                is LocationUIEvent.ShowLocations -> {
                    setState(
                        oldState.copy(
                            isLoading = false,
                            isError = false,
                            locations = event.locations
                        )
                    )
                }
                is LocationUIEvent.ShowResidents -> {
                    setState(
                        oldState.copy(
                            isLoading = false,
                            isError = false,
                            residents = event.residents
                        )
                    )
                }
            }
        }
    }
}