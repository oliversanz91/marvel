package com.oliversanz.marvel.data.mapper

import com.oliversanz.marvel.data.network.entity.HeroResponseItem
import com.oliversanz.marvel.domain.model.HeroListModel
import com.oliversanz.marvel.domain.model.ImageModel

class ToHeroListMapper {

    fun mapToListModel(responseItem: HeroResponseItem): HeroListModel{
        return HeroListModel(
            id = responseItem.id.orEmpty(),
            name = responseItem.name.orEmpty(),
            image = ImageModel(responseItem.thumbnail?.path.orEmpty(), responseItem.thumbnail?.extension.orEmpty())
        )
    }

}