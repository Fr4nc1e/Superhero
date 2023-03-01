package com.comic.superhero.di

import com.comic.superhero.feature.home.data.api.HomeApi
import com.comic.superhero.feature.home.data.repository.HomeRepositoryImpl
import com.comic.superhero.feature.home.domain.repository.HomeRespository
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
}
