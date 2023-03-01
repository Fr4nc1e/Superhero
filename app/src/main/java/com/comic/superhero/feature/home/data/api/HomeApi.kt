package com.comic.superhero.feature.home.data.api

import com.comic.superhero.feature.home.data.dto.SuperHeroDto
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApi {
    @GET("api/182017374561797/{character-id}")
    suspend fun getSuperHero(
        @Path("character-id") heroId: String
    ): SuperHeroDto
}
