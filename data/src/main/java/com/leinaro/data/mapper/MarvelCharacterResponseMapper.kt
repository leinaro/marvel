package com.leinaro.data.mapper

import com.leinaro.apis.data.MarvelCharacterResponse
import com.leinaro.data.data.MarvelCharacterData

private const val STANDARD_SMALL = "standard_small"
private const val LANDSCAPE_LARGE = "landscape_large"

internal fun List<MarvelCharacterResponse>.toDomainModel(): List<MarvelCharacterData> =
  this.map { marvelCharacterResponse ->
    marvelCharacterResponse.toDomainModel()
  }

internal fun MarvelCharacterResponse.toDomainModel() = MarvelCharacterData(
  id = this.id,
  name = this.name,
  description = this.description,
  thumbnailUrl = "${this.thumbnail.path}/$STANDARD_SMALL.${this.thumbnail.extension}",
  landscapeUrl = "${this.thumbnail.path}/$LANDSCAPE_LARGE.${this.thumbnail.extension}",
  comics = listOf()
)
