package com.akanbi.rickandmorty.domain.mapper

import com.akanbi.rickandmorty.domain.model.LocationModel
import com.akanbi.rickandmorty.network.model.location.Result

class LocationMapper: GeneralMapper<Result, LocationModel> {

    override fun convert(model: Result): LocationModel =
        LocationModel(
            id = model.id,
            name = model.name,
            dimension = model.dimension,
            url = model.url,
            type = model.type,
            created = model.created,
            residents = model.residents
        )
}