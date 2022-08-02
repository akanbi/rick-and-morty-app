package com.akanbi.rickandmorty.domain.core

import com.akanbi.rickandmorty.network.ResponseError

interface UseCase<S> {

    suspend fun execute(
        parameters: ParametersDTO = ParametersDTO{},
        onSuccess: (S) -> Unit,
        onError: (ResponseError) -> Unit
    )

}