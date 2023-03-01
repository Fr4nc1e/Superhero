package com.comic.superhero.di

import com.comic.superhero.core.util.Constant
import com.comic.superhero.feature.home.data.api.HomeApi
import com.comic.superhero.feature.search.data.api.SearchApi
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
object NetworkModule {
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
        .baseUrl(Constant.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(HomeApi::class.java)

    @Provides
    @Singleton
    fun provideSearchApi(client: OkHttpClient): SearchApi = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SearchApi::class.java)
}
