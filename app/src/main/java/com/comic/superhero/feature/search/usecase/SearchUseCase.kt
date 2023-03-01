package com.comic.superhero.feature.search.usecase

import com.comic.superhero.feature.search.domain.repository.SearchRepository

class SearchUseCase(
    private val repository: SearchRepository
) {
    operator fun invoke(name: String) = repository.searchHero(name)
}
