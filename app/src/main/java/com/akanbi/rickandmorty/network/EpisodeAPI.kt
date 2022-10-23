package com.akanbi.rickandmorty.network

import com.akanbi.rickandmorty.network.model.episode.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeAPI {

    @GET("episode")
    suspend fun list()

    @GET("episode/{id}")
    suspend fun findById(@Path("id") id: String): Result

    @GET("episode")
    suspend fun searchBy(
        @Query("name") name: String = "",
        @Query("episode") episode: Int = 0,
    )

}