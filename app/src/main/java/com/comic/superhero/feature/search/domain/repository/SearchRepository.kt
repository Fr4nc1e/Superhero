package com.comic.superhero.feature.search.domain.repository

import com.comic.superhero.core.data.util.Resource
import com.comic.superhero.feature.search.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchHero(name: String): Flow<Resource<List<Item?>>>
}
