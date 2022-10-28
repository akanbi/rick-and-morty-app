package com.akanbi.rickandmorty.presentation.location

import androidx.compose.runtime.Immutable
import com.akanbi.rickandmorty.domain.model.Character
import com.akanbi.rickandmorty.domain.model.LocationModel
import com.akanbi.rickandmorty.presentation.components.model.Residents
import com.akanbi.rickandmorty.presentation.components.model.SimpleElement
import com.akanbi.rickandmorty.presentation.core.UIEvent
import com.akanbi.rickandmorty.presentation.core.UIState

@Immutable
sealed class LocationUIEvent: UIEvent {
    data class OnLoading(val isLoading: Boolean): LocationUIEvent()
    data class OnError(val isError: Boolean): LocationUIEvent()
    data class ShowLocations(val locations: List<LocationModel>) : LocationUIEvent()
    data class ShowResidents(val residents: List<Residents>, val locations: List<LocationModel>): LocationUIEvent()
}

@Immutable
data class LocationUIState(
    val isLoading: Boolean,
    val isError: Boolean,
    val locations: List<LocationModel>,
    val residents: List<Residents>
) : UIState {

    companion object {
        fun initial() = LocationUIState(
            isLoading = true,
            isError = false,
            locations = emptyList(),
            residents = emptyList()
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, locations.size: ${locations.size}, residents.size: ${residents.size}"
    }
}