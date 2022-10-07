package com.leinaro.apis.data

data class MarvelCharactersResponse<T>(
  val code: String,
  val status: String,
  val copyright: String,
  val attributionText: String,
  val data: MarvelDataResponse<T>,
)

data class MarvelDataResponse<T>(
  val offset: Int,
  val limit: Int,
  val total: Int,
  val count: Int,
  val results: List<T>?,
)

data class MarvelCharacterResponse(
  val id: Long,
  val name: String,
  val description: String,
  val thumbnail: Thumbnail,
)

data class Thumbnail(
  val path: String,
  val extension: String,
)

data class ComicsResponse(
  val id: Long,
  val title: String,
  val description: String,
  val thumbnail: Thumbnail,
)