package com.leinaro.domain

import com.leinaro.data.MarvelCharacter
import kotlinx.coroutines.flow.Flow

interface Repository {
  fun getCharacters(): Flow<ApiResponse<List<MarvelCharacter>>>
}