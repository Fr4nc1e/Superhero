package com.comic.superhero.feature.search.data.repository

import com.comic.superhero.R
import com.comic.superhero.core.data.util.Resource
import com.comic.superhero.core.presentation.ui.util.UiText
import com.comic.superhero.feature.search.data.api.SearchApi
import com.comic.superhero.feature.search.domain.model.Item
import com.comic.superhero.feature.search.domain.repository.SearchRepository
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class SearchRepositoryImpl(
    private val api: SearchApi
) : SearchRepository {
    override fun searchHero(name: String): Flow<Resource<List<Item?>>> {
        return flow {
            emit(Resource.Loading(true))
            val result = try {
                api.searchSuperHero(name).toSearchedHeroes().results
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(uiText = UiText.StringResource(R.string.fail_connect)))
                emptyList()
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(uiText = UiText.StringResource(R.string.fail_connect)))
                emptyList()
            }
            emit(Resource.Success(data = result))
            emit(Resource.Loading(false))
        }
    }
}
