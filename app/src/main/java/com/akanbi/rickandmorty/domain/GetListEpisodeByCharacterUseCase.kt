package com.akanbi.rickandmorty.domain

import com.akanbi.rickandmorty.common.network.ResultType
import com.akanbi.rickandmorty.common.network.handleResultType
import com.akanbi.rickandmorty.data.EpisodeRepository
import com.akanbi.rickandmorty.domain.core.ParametersDTO
import com.akanbi.rickandmorty.domain.core.UseCase
import com.akanbi.rickandmorty.network.ResponseError
import com.akanbi.rickandmorty.network.model.episode.Result
import com.akanbi.rickandmorty.presentation.components.model.SimpleElement
import javax.inject.Inject

class GetListEpisodeByCharacterUseCase @Inject constructor(
    private val repository: EpisodeRepository
) : UseCase<List<SimpleElement>> {

    override suspend fun execute(
        parameters: ParametersDTO,
        onSuccess: (List<SimpleElement>) -> Unit,
        onError: (ResponseError) -> Unit
    ) {
        val episodesResult = mutableListOf<SimpleElement>()
        val ids = parameters.valueAsList("ids")
        ids.forEach { id ->
            val result: ResultType<Result> = repository.findById(id as String)
            result.handleResultType(
                success = {
                    episodesResult.add(SimpleElement(it.episode, it.name))
                },
                error = {
                    onError(it)
                }
            )
        }
        onSuccess(episodesResult)
    }
}