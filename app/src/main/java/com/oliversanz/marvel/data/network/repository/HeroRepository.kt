package com.oliversanz.marvel.data.network.repository

import com.oliversanz.marvel.data.network.entity.HeroResponseItem

interface HeroRepository {

    suspend fun getHeroList(offset: Int? = 0): List<HeroResponseItem>
    suspend fun getHeroDetail(heroId: String): HeroResponseItem

}