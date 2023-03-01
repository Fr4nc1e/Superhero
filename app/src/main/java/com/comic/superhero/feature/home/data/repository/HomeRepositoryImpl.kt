package com.comic.superhero.feature.home.data.repository

import com.comic.superhero.R
import com.comic.superhero.core.data.util.Resource
import com.comic.superhero.core.presentation.ui.util.UiText
import com.comic.superhero.feature.home.data.api.HomeApi
import com.comic.superhero.feature.home.domain.model.SuperHero
import com.comic.superhero.feature.home.domain.repository.HomeRespository
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class HomeRepositoryImpl(
    private val api: HomeApi
) : HomeRespository {
    override fun getSuperHero(heroId: String): Flow<Resource<SuperHero>> {
        return flow {
            emit(Resource.Loading(true))
            val superHero = try {
                api.getSuperHero(heroId).toSuperHero()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(uiText = UiText.StringResource(R.string.fail_connect)))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(uiText = UiText.StringResource(R.string.fail_connect)))
                null
            }
            emit(Resource.Success(data = superHero))
            emit(Resource.Loading(false))
        }
    }
}
