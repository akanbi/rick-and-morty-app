package com.akanbi.rickandmorty.domain

import com.akanbi.rickandmorty.common.network.ResultType
import com.akanbi.rickandmorty.common.network.handleResultType
import com.akanbi.rickandmorty.data.CharacterRepository
import com.akanbi.rickandmorty.domain.core.ParametersDTO
import com.akanbi.rickandmorty.domain.core.UseCase
import com.akanbi.rickandmorty.domain.mapper.CharacterMapper
import com.akanbi.rickandmorty.domain.model.Character
import com.akanbi.rickandmorty.domain.model.CharacterUI
import com.akanbi.rickandmorty.network.ResponseError
import com.akanbi.rickandmorty.network.model.character.CharacterResponse
import com.akanbi.rickandmorty.network.model.character.ResultCharacter
import javax.inject.Inject

class GetListCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository,
    private val mapper: CharacterMapper
) : UseCase<CharacterUI> {
    private var currentPage = 1

    override suspend fun execute(
        parameters: ParametersDTO,
        onSuccess: (CharacterUI) -> Unit,
        onError: (ResponseError) -> Unit
    ) {
        val result: ResultType<CharacterResponse> = repository.list(currentPage)

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
        response: CharacterResponse,
        onSuccess: (CharacterUI) -> Unit,
        onError: (ResponseError) -> Unit
    ) {
        if (response.results.isEmpty() && currentPage == 1)
            onError(ResponseError())
        else if (currentPage != response.info.pages + 1) {
            val characterUI = buildCharacterUI(response)
            onSuccess(characterUI)

            incrementPage()
        } else onSuccess(CharacterUI())
    }

    private fun buildCharacterUI(response: CharacterResponse) =
        CharacterUI(
            characterList = buildCharacterList(response.results),
            pagination = response.info
        )

    private fun buildCharacterList(results: List<ResultCharacter>): MutableList<Character> {
        val characterList = mutableListOf<Character>()
        results.forEach { result ->
            characterList.add(mapper.convert(result))
        }
        return characterList
    }

    private fun incrementPage() {
        currentPage++
    }

    fun resetPage() {
        currentPage = 1
    }
}