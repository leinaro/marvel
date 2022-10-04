package com.leinaro.domain

import com.leinaro.data.MarvelCharacter
import com.leinaro.domain.domain_status.ApiResponse
import kotlinx.coroutines.flow.Flow

interface Repository {
  fun getCharacterDetails(characterId: Long): Flow<ApiResponse<MarvelCharacter>>
}