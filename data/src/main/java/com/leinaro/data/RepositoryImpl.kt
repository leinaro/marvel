package com.leinaro.data

import com.leinaro.apis.services.CharactersServices
import com.leinaro.data.data.MarvelCharacterData
import com.leinaro.data.mapper.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
  private val charactersServices: CharactersServices,

) : Repository {
  override fun getCharacterDetails(characterId: Long): Flow<MarvelCharacterData?> = flow {
    val characterResponse = charactersServices.fetchCharacter(characterId)
    val comicsResponse = charactersServices.fetchCharacterComics(characterId)
    val character = characterResponse.data.results?.first()?.toDomainModel()?.apply {
      comics = comicsResponse.data.results.orEmpty().toDomainModel()
    }
    emit(character)
  }

  override suspend fun fetchesListsOfCharacters(
    limit: Int,
    offset: Int,
    nameStartsWith: String?,
  ): List<MarvelCharacterData> {
    return charactersServices.fetchesListsOfCharacters(limit, offset, nameStartsWith)
      .data.results.orEmpty().toDomainModel()
  }
}
