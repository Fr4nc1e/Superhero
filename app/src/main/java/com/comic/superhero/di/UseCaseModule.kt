package com.comic.superhero.di

import com.comic.superhero.feature.home.domain.repository.HomeRespository
import com.comic.superhero.feature.home.usecase.HomeUseCase
import com.comic.superhero.feature.home.usecase.component.GetSuperHeroUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideHomeUseCase(repository: HomeRespository) = HomeUseCase(
        getSuperHero = GetSuperHeroUseCase(repository)
    )
}
