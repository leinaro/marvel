package com.leinaro.data

import com.leinaro.data.data.MarvelCharacterData
import kotlinx.coroutines.flow.Flow

interface Repository {
  fun getCharacterDetails(characterId: Long): Flow<MarvelCharacterData?>
  suspend fun fetchesListsOfCharacters(
    limit: Int,
    offset: Int,
    nameStartsWith: String? = null,
  ): List<MarvelCharacterData>
}