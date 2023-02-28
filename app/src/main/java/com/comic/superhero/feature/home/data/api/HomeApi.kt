package com.comic.superhero.feature.home.data.api

import com.comic.superhero.feature.home.data.dto.SuperHeroDto
import retrofit2.http.GET

interface HomeApi {
    @GET("/character-id")
    suspend fun getSuperHero(heroId: String): SuperHeroDto
}
