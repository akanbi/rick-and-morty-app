package com.akanbi.rickandmorty.network

import retrofit2.http.GET

interface CharacterAPI {

    @GET("character")
    fun list()

}