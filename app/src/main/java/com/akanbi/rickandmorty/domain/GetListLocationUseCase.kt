package com.akanbi.rickandmorty.domain

import com.akanbi.rickandmorty.data.LocationRepository
import com.akanbi.rickandmorty.domain.core.ParametersDTO
import com.akanbi.rickandmorty.domain.core.UseCase
import com.akanbi.rickandmorty.network.ResponseError
import com.akanbi.rickandmorty.network.model.character.Location
import javax.inject.Inject

class GetListLocationUseCase @Inject constructor(
    private val repository: LocationRepository
) : UseCase<List<Location>> {

    override suspend fun execute(
        parameters: ParametersDTO,
        onSuccess: (List<Location>) -> Unit,
        onError: (ResponseError) -> Unit
    ) {
        TODO("Not yet implemented")
    }

}