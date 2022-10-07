package com.leinaro.data.mapper

import com.leinaro.apis.data.ComicsResponse
import com.leinaro.data.data.ComicData

private const val PORTRAIT_XLARGE = "portrait_xlarge"

internal fun List<ComicsResponse>.toDomainModel(): List<ComicData> =
  this.map { marvelCharacterResponse ->
    marvelCharacterResponse.toDomainModel()
  }

internal fun ComicsResponse.toDomainModel() = ComicData(
  name = this.title,
  imageUrl = "${this.thumbnail.path}/$PORTRAIT_XLARGE.${this.thumbnail.extension}",
)
