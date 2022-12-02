package com.akanbi.rickandmorty.domain

import com.akanbi.rickandmorty.common.network.ResultType
import com.akanbi.rickandmorty.common.network.handleResultType
import com.akanbi.rickandmorty.data.CharacterRepository
import com.akanbi.rickandmorty.domain.core.ParametersDTO
import com.akanbi.rickandmorty.domain.core.UseCase
import com.akanbi.rickandmorty.network.ResponseError
import com.akanbi.rickandmorty.network.model.character.ResultCharacter
import com.akanbi.rickandmorty.presentation.components.model.Residents
import javax.inject.Inject

class GetResidentsOnLocationByIdsUseCase @Inject constructor(
    private val repository: CharacterRepository
): UseCase<List<Residents>> {

    override suspend fun execute(
        parameters: ParametersDTO,
        onSuccess: (List<Residents>) -> Unit,
        onError: (ResponseError) -> Unit
    ) {
        val residents = mutableListOf<Residents>()
        val ids = parameters.valueAsList("ids")
        ids.forEach { id ->
            val result: ResultType<ResultCharacter> = repository.findById(id as String)
            result.handleResultType(
                success = {
                    residents.add(Residents(it.name, it.image))
                },
                error = {
                    onError(it)
                    return@handleResultType
                }
            )
        }
        onSuccess(residents)
    }

}