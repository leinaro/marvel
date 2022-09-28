package com.leinaro.apis.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


private const val MARVEL_BASE_URL = "https://gateway.marvel.com:443"
@InstallIn(SingletonComponent::class)
@Module()
object NetworkModule {
  @Provides
  @Singleton
  fun providesRetrofit(): Retrofit {
    return Retrofit.Builder()
      .baseUrl(MARVEL_BASE_URL)
      .build()
  }
}