package com.akanbi.rickandmorty.domain

import com.akanbi.rickandmorty.common.network.ResultType
import com.akanbi.rickandmorty.common.network.handleResultType
import com.akanbi.rickandmorty.data.CharacterRepository
import com.akanbi.rickandmorty.domain.core.ParametersDTO
import com.akanbi.rickandmorty.domain.core.UseCase
import com.akanbi.rickandmorty.domain.model.Character
import com.akanbi.rickandmorty.network.ResponseError
import com.akanbi.rickandmorty.network.model.CharacterResponse
import javax.inject.Inject

class GetListCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
) : UseCase<Character> {

    override suspend fun execute(
        parameters: ParametersDTO,
        onSuccess: (Character) -> Unit,
        onError: (ResponseError) -> Unit
    ) {
        val result: ResultType<CharacterResponse> = repository.list()

        result.handleResultType(
            success = {

            },
            error = {

            }
        )
    }

}