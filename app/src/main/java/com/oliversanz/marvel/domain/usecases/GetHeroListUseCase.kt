package com.oliversanz.marvel.domain.usecases

import com.oliversanz.marvel.data.mapper.ToHeroListMapper
import com.oliversanz.marvel.data.network.repository.HeroRepository
import com.oliversanz.marvel.domain.model.HeroListModel

class GetHeroListUseCase(
    private val repository: HeroRepository,
    private val mapper: ToHeroListMapper
) {

    suspend fun invoke(offset: Int? = 0): List<HeroListModel>{
        return repository.getHeroList(offset)
            .filterNot { it.id.isNullOrBlank() or it.name.isNullOrBlank() }
            .map(mapper::mapToListModel)
    }

}