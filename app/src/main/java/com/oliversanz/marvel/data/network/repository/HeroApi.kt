package com.oliversanz.marvel.data.network.repository

import com.oliversanz.marvel.data.network.entity.HeroDetailResponse
import com.oliversanz.marvel.data.network.entity.HeroListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HeroApi {

    @GET(ENDPOINT_HERO_LIST)
    suspend fun getHeroList(
        @Query(QUERY_OFFSET) offset: Int,
        @Query(QUERY_LIMIT) limit: Int = 20
    ): HeroListResponse


    @GET(ENDPOINT_HERO_DETAIL)
    suspend fun getHeroDetail(@Path(PATH_HERO_ID) heroId: String): HeroDetailResponse

    companion object {
        private const val QUERY_OFFSET = "offset"
        private const val QUERY_LIMIT = "limit"
        private const val PATH_HERO_ID = "heroId"
        const val PAGE_SIZE = 20

        private const val ENDPOINT_HERO_LIST = "v1/public/characters"
        private const val ENDPOINT_HERO_DETAIL = "v1/public/characters/{$PATH_HERO_ID}"

    }

}