package com.akanbi.rickandmorty.network.model

import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("next")
    val next: String = "0",
    @SerializedName("pages")
    val pages: Int = 0,
    @SerializedName("prev")
    val prev: Any = ""
)