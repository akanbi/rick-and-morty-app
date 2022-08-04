package com.akanbi.rickandmorty.domain.mapper

interface GeneralMapper<in E, out S> {

    fun convert(model: E) : S

}