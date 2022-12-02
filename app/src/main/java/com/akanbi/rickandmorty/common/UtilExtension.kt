package com.akanbi.rickandmorty.common

fun idsToListByUrl(urls: List<String>): List<String> {
    val ids = mutableListOf<String>()
    urls.forEach {
        ids.add(getIdByUrl(it))
    }
    return ids
}

private fun getIdByUrl(url: String) = url.split("/").last()