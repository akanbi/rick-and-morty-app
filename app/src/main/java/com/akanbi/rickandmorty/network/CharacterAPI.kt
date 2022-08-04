package com.akanbi.rickandmorty.network

import com.akanbi.rickandmorty.domain.type.CharacterGender
import com.akanbi.rickandmorty.domain.type.CharacterStatus
import com.akanbi.rickandmorty.network.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterAPI {

    @GET("character")
    suspend fun list(): CharacterResponse

    @GET("character/{id}")
    suspend fun findById(@Path("id") id: String)

    @GET("character")
    suspend fun searchBy(
        @Query("name") name: String = "",
        @Query("status") status: CharacterStatus?,
        @Query("species") species: String = "",
        @Query("type") type: String = "",
        @Query("gender") gender: CharacterGender?
    )
}