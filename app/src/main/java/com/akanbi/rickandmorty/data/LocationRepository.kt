package com.akanbi.rickandmorty.data

import com.akanbi.rickandmorty.common.network.safeApiCall
import com.akanbi.rickandmorty.network.LocationAPI
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val api: LocationAPI
) {

    suspend fun list() = safeApiCall {
        api.list()
    }

    suspend fun findById(id: String) = safeApiCall {
        api.findById(id)
    }

    suspend fun searchBy(
        name: String = "",
        type: String = "",
        dimension: String
    ) = safeApiCall {
        api.searchBy(
            name, type, dimension
        )
    }

}