package com.oliversanz.marvel.data.network.repository

import com.oliversanz.marvel.data.network.entity.HeroListItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class HeroRepositoryImpl(
    private val heroApi: HeroApi,
    private val dispatcher: CoroutineDispatcher
) : HeroRepository {

    override suspend fun getHeroList(offset: Int?): List<HeroListItem> {
        return withContext(dispatcher) {
            val response = heroApi.getHeroList(offset ?: 0)
            response.responseData?.heroList.orEmpty()
        }
    }

}