package com.akanbi.rickandmorty.data

import com.akanbi.rickandmorty.common.network.safeApiCall
import com.akanbi.rickandmorty.domain.type.CharacterGender
import com.akanbi.rickandmorty.domain.type.CharacterStatus
import com.akanbi.rickandmorty.network.CharacterAPI
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val api: CharacterAPI
) {

    suspend fun list() = safeApiCall {
        api.list()
    }

    suspend fun findById(id: String) = safeApiCall {
        api.findById(id)
    }

    suspend fun searchBy(
        name: String = "",
        status: CharacterStatus?,
        species: String = "",
        type: String = "",
        gender: CharacterGender?
    ) = safeApiCall {
        api.searchBy(
            name, status, species, type, gender
        )
    }

}