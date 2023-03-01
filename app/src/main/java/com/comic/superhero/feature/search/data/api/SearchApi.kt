package com.comic.superhero.feature.search.data.api

import com.comic.superhero.feature.search.data.dto.SearchedHeroesDto
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchApi {
    @GET("api/182017374561797/search/{name}")
    suspend fun searchSuperHero(
        @Path("name") name: String
    ): SearchedHeroesDto
}
