package com.leinaro.apis.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.leinaro.apis.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
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
  fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return interceptor
  }

  @Provides
  @Singleton
  fun providesChuckerInterceptor(
    @ApplicationContext context: Context,
  ): ChuckerInterceptor {
    return ChuckerInterceptor.Builder(context).build()
  }

  @Provides
  @Singleton
  fun providesAuthMarvelInterceptor(): Interceptor {
    val authMarvelInterceptor = Interceptor { chain ->
      val original: Request = chain.request()
      val originalHttpUrl: HttpUrl = original.url
      val ts = "1"
      val privateKey = BuildConfig.marvel_privateKey
      val apiKey = BuildConfig.marvel_apiKey
      val md5 = md5(ts + privateKey + apiKey)
      val url = originalHttpUrl.newBuilder()
        .addQueryParameter("apikey", apiKey)
        .addQueryParameter("ts", ts)
        .addQueryParameter("hash", md5)
        .build()

      val requestBuilder: Request.Builder = original.newBuilder()
        .url(url)

      val request: Request = requestBuilder.build()
      chain.proceed(request)
    }

    return authMarvelInterceptor
  }

  @Provides
  @Singleton
  fun providesNetworkInterceptor(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    chuckerInterceptor: ChuckerInterceptor,
    authMarvelInterceptor: Interceptor,
  ): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(httpLoggingInterceptor)
      .addInterceptor(chuckerInterceptor)
      .addInterceptor(authMarvelInterceptor)
      .build()
  }
}

fun md5(input: String): String {
  val md = MessageDigest.getInstance("MD5")
  return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}