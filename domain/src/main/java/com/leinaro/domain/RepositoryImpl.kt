package com.leinaro.domain

import com.leinaro.apis.data.MarvelCharacterResponse
import com.leinaro.apis.services.CharactersServices
import com.leinaro.data.MarvelCharacter
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
  //private val charactersServices: CharactersServices,
  //private val charactersSource: CharactersSource,
) : Repository {
//  override fun getCharactersSource(): CharactersSource = charactersSource
}

fun List<MarvelCharacterResponse>.toDomainModel(): List<MarvelCharacter> =
  this.map { marvelCharacterResponse ->
    marvelCharacterResponse.toDomainModel()
  }

fun MarvelCharacterResponse.toDomainModel() = MarvelCharacter(
  id = this.id,
  name = this.name,
  thumbnailUrl = "${this.thumbnail.path}/$STANDARD_SMALL.${this.thumbnail.extension}"
)

private const val STANDARD_SMALL = "standard_small"
