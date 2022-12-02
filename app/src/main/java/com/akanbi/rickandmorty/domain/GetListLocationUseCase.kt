package com.akanbi.rickandmorty.domain

import com.akanbi.rickandmorty.common.network.ResultType
import com.akanbi.rickandmorty.common.network.handleResultType
import com.akanbi.rickandmorty.data.LocationRepository
import com.akanbi.rickandmorty.domain.core.ParametersDTO
import com.akanbi.rickandmorty.domain.core.UseCase
import com.akanbi.rickandmorty.domain.mapper.LocationMapper
import com.akanbi.rickandmorty.domain.model.LocationModel
import com.akanbi.rickandmorty.domain.model.LocationUI
import com.akanbi.rickandmorty.network.ResponseError
import com.akanbi.rickandmorty.network.model.location.LocationResponse
import com.akanbi.rickandmorty.network.model.location.ResultLocation
import javax.inject.Inject

class GetListLocationUseCase @Inject constructor(
    private val repository: LocationRepository,
    private val mapper: LocationMapper
) : UseCase<LocationUI> {

    override suspend fun execute(
        parameters: ParametersDTO,
        onSuccess: (LocationUI) -> Unit,
        onError: (ResponseError) -> Unit
    ) {
        val result: ResultType<LocationResponse> = repository.list(1)

        result.handleResultType(
            success = {
                successFlow(it, onSuccess, onError)
            },
            error = {
                onError(it)
            }
        )
    }

    private fun successFlow(
        response: LocationResponse,
        onSuccess: (LocationUI) -> Unit,
        onError: (ResponseError) -> Unit
    ) {
        if (response.results.isEmpty())
            onError(ResponseError())
        else {
            val locationUI = buildLocationUI(response)
            onSuccess(locationUI)
        }
    }

    private fun buildLocationUI(response: LocationResponse) =
        LocationUI(
            locationList = buildLocationList(response.results),
            pagination = response.info
        )

    private fun buildLocationList(resultLocations: List<ResultLocation>): MutableList<LocationModel> {
        val locations = mutableListOf<LocationModel>()
        resultLocations.forEach { currentLocation ->
            locations.add(mapper.convert(currentLocation))
        }
        return locations
    }

}