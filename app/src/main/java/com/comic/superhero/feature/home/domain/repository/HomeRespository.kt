package com.comic.superhero.feature.home.domain.repository

import com.comic.superhero.core.data.util.Resource
import com.comic.superhero.feature.home.domain.model.SuperHero
import kotlinx.coroutines.flow.Flow

interface HomeRespository {
    fun getSuperHero(heroId: String): Flow<Resource<SuperHero>>
}
