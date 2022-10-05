package com.leinaro.data

data class MarvelCharacter(
  val id: Long,
  val name: String,
  val thumbnailUrl: String,
  val landscapeUrl: String,
  val comics: List<Comic>,
)

data class Comic(
  val name: String,
)
