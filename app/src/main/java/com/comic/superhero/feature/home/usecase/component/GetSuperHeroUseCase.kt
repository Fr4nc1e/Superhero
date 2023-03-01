package com.comic.superhero.feature.home.usecase.component

import com.comic.superhero.feature.home.domain.repository.HomeRespository

class GetSuperHeroUseCase(
    private val repository: HomeRespository
) {
    operator fun invoke(heroId: String) = repository.getSuperHero(heroId)
}
