package com.oliversanz.marvel.data.network.repository

import com.oliversanz.marvel.data.network.entity.HeroListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroApi {

    @GET(ENDPOINT_HERO_LIST)
    suspend fun getHeroList(
        @Query(QUERY_OFFSET) offset: Int,
        @Query(QUERY_LIMIT) limit: Int = 20
    ): HeroListResponse

    companion object {
        private const val ENDPOINT_HERO_LIST = "v1/public/characters"

        private const val QUERY_OFFSET = "offset"
        private const val QUERY_LIMIT = "limit"
    }

}