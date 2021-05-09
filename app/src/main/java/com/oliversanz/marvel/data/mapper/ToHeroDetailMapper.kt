package com.oliversanz.marvel.data.mapper

import com.oliversanz.marvel.data.network.entity.HeroResponseItem
import com.oliversanz.marvel.data.network.entity.Url
import com.oliversanz.marvel.domain.model.HeroModel
import com.oliversanz.marvel.domain.model.ImageModel
import com.oliversanz.marvel.domain.model.UrlModel

class ToHeroDetailMapper {

    fun toModel(hero: HeroResponseItem): HeroModel {
        return HeroModel(
            id = hero.id.orEmpty(),
            name = hero.name.orEmpty(),
            description = hero.description.orEmpty(),
            image = ImageModel(hero.thumbnail?.path.orEmpty(), hero.thumbnail?.extension.orEmpty()),
            url = hero.urls?.filterNot { it.url.isNullOrBlank() or it.type.isNullOrBlank() }.orEmpty().map(::toUrlModelMapper)
        )
    }

    private fun toUrlModelMapper(url: Url) : UrlModel {
        return UrlModel(url.type.orEmpty(), url.url.orEmpty())
    }

}