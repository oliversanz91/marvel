package com.oliversanz.marvel.data.mapper

import com.oliversanz.marvel.data.network.entity.HeroListItem
import com.oliversanz.marvel.domain.model.HeroListModel
import com.oliversanz.marvel.domain.model.ImageModel

class HeroListMapper {

    fun mapToListModel(listItem: HeroListItem): HeroListModel{
        return HeroListModel(
            id = listItem.id.orEmpty(),
            name = listItem.name.orEmpty(),
            image = ImageModel(listItem.thumbnail?.path.orEmpty(), listItem.thumbnail?.extension.orEmpty())
        )
    }

}