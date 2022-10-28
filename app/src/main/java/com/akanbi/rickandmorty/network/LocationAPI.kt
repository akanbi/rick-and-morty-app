package com.akanbi.rickandmorty.network

import com.akanbi.rickandmorty.network.model.location.LocationResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationAPI {

    @GET("location")
    suspend fun list(@Query("page") page: Int): LocationResponse

    @GET("location/{id}")
    suspend fun findById(@Path("id") id: String)

    @GET("location")
    suspend fun searchBy(
        @Query("name") name: String = "",
        @Query("type") type: String = "",
        @Query("dimension") dimension: String = ""
    )

}