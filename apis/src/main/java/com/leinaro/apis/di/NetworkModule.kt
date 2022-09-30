package com.leinaro.apis.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val MARVEL_BASE_URL = "https://gateway.marvel.com/"

@InstallIn(SingletonComponent::class)
@Module()
object NetworkModule {
  @Provides
  @Singleton
  fun providesRetrofit(
    interceptor: OkHttpClient
  ): Retrofit {
    return Retrofit.Builder()
      .baseUrl(MARVEL_BASE_URL)
      .client(interceptor)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Provides
  @Singleton
  fun providesNetworkInterceptor(
    @ApplicationContext context: Context
  ): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    return OkHttpClient.Builder()
      .addInterceptor(ChuckerInterceptor.Builder(context).build())
      .addInterceptor(interceptor)
      .build()
  }
}