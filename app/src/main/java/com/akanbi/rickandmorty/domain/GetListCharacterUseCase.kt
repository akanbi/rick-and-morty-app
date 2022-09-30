package com.akanbi.rickandmorty.domain

import com.akanbi.rickandmorty.common.network.ResultType
import com.akanbi.rickandmorty.common.network.handleResultType
import com.akanbi.rickandmorty.data.CharacterRepository
import com.akanbi.rickandmorty.domain.core.ParametersDTO
import com.akanbi.rickandmorty.domain.core.UseCase
import com.akanbi.rickandmorty.domain.mapper.CharacterMapper
import com.akanbi.rickandmorty.domain.model.Character
import com.akanbi.rickandmorty.network.ResponseError
import com.akanbi.rickandmorty.network.model.CharacterResponse
import javax.inject.Inject

class GetListCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository,
    private val mapper: CharacterMapper
) : UseCase<List<Character>> {

    override suspend fun execute(
        parameters: ParametersDTO,
        onSuccess: (List<Character>) -> Unit,
        onError: (ResponseError) -> Unit
    ) {
        val result: ResultType<CharacterResponse> = repository.list()

        result.handleResultType(
            success = {
                onSuccess(buildResultOnSuccessFlow(it))
            },
            error = {
                onError(it)
            }
        )
    }

    private fun buildResultOnSuccessFlow(response: CharacterResponse): MutableList<Character> {
        val characterList = mutableListOf<Character>()
        response.results.forEach { result ->
            characterList.add(mapper.convert(result))
        }
        return characterList
    }

}