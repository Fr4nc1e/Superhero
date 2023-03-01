package com.comic.superhero.di

import com.comic.superhero.feature.home.data.api.HomeApi
import com.comic.superhero.feature.home.data.repository.HomeRepositoryImpl
import com.comic.superhero.feature.home.domain.repository.HomeRespository
import com.comic.superhero.feature.search.data.api.SearchApi
import com.comic.superhero.feature.search.data.repository.SearchRepositoryImpl
import com.comic.superhero.feature.search.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideHomeRepository(api: HomeApi): HomeRespository {
        return HomeRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(api: SearchApi): SearchRepository {
        return SearchRepositoryImpl(api)
    }
}
