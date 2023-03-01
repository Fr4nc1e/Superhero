package com.comic.superhero.di

import com.comic.superhero.core.util.Constant.BASE_URL
import com.comic.superhero.feature.home.data.api.HomeApi
import com.comic.superhero.feature.home.data.repository.HomeRepositoryImpl
import com.comic.superhero.feature.home.domain.repository.HomeRespository
import com.comic.superhero.feature.home.usecase.HomeUseCase
import com.comic.superhero.feature.home.usecase.component.GetSuperHeroUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    @Provides
    @Singleton
    fun provideHomeApi(client: OkHttpClient): HomeApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(HomeApi::class.java)

    @Provides
    @Singleton
    fun provideHomeRepository(api: HomeApi): HomeRespository {
        return HomeRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideHomeUseCase(repository: HomeRespository) = HomeUseCase(
        getSuperHero = GetSuperHeroUseCase(repository)
    )
}
