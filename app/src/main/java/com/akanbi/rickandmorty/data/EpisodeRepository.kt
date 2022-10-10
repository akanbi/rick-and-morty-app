package com.akanbi.rickandmorty.data

import com.akanbi.rickandmorty.common.network.safeApiCall
import com.akanbi.rickandmorty.network.EpisodeAPI
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val api: EpisodeAPI
) {

    suspend fun list() = safeApiCall {
        api.list()
    }

    suspend fun findById(id: String) = safeApiCall {
        api.findById(id)
    }

    suspend fun searchBy(
        name: String = "",
        episode: Int = 0
    ) = safeApiCall {
        api.searchBy(
            name, episode
        )
    }

}