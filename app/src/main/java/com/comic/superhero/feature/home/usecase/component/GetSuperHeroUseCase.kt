package com.comic.superhero.feature.home.usecase.component

import com.comic.superhero.feature.home.data.repository.HomeRepository

class GetSuperHeroUseCase(
    private val repository: HomeRepository
) {
    operator fun invoke(heroId: String) = repository.getSuperHero(heroId)
}
