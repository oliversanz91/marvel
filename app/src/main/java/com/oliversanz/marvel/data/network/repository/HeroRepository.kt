package com.oliversanz.marvel.data.network.repository

import com.oliversanz.marvel.data.network.entity.HeroListItem

interface HeroRepository {

    suspend fun getHeroList(offset: Int? = 0): List<HeroListItem>

}