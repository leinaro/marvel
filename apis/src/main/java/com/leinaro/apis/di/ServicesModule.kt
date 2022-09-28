package com.leinaro.apis.di

import com.leinaro.apis.services.CharactersServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module()
object ServicesModule {
  @Provides
  fun providesCharactersServices(retrofit: Retrofit): CharactersServices {
    return retrofit.create(CharactersServices::class.java)
  }
}