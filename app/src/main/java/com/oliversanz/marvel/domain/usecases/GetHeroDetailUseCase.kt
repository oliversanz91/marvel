package com.oliversanz.marvel.domain.usecases

import com.oliversanz.marvel.data.mapper.ToHeroDetailMapper
import com.oliversanz.marvel.data.network.repository.HeroRepository
import com.oliversanz.marvel.domain.model.HeroModel

class GetHeroDetailUseCase(
    private val repository: HeroRepository,
    private val mapper: ToHeroDetailMapper
) {

    suspend fun invoke(heroId: String): HeroModel {
        return repository.getHeroDetail(heroId).let {
            mapper.toModel(it)
        }
    }

}