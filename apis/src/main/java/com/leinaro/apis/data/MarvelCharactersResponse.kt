package com.leinaro.apis.data

data class MarvelCharactersResponse(
  val code: String,
  val status: String,
  val copyright: String,
  val attributionText: String,
  val data: MarvelDataResponse,
)

data class MarvelDataResponse(
  val offset: Int,
  val limit: Int,
  val total: Int,
  val count: Int,
  val results: List<MarvelCharacterResponse>?,
)

data class MarvelCharacterResponse(
  val id: Long,
  val name: String,
  val thumbnail: Thumbnail,
  val comics: Comics,
)

data class Thumbnail(
  val path: String,
  val extension: String,
)

data class Comics(
  val available: Int,
  val items: List<ComicItem>,
)

data class ComicItem(
  val name: String,
)
