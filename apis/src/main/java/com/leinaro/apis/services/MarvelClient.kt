package com.leinaro.apis.services

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton
/*
@Singleton
class MarvelClient @Inject constructor(
  @ApplicationContext private val context: Context,
  private val charactersServices: CharactersServices,
  private val retrofit: Retrofit,
  @IODisp private val dispatchers: CoroutineDispatcher,
  ) {
   fun getCharacters() {
      withContext()
  }
}*/