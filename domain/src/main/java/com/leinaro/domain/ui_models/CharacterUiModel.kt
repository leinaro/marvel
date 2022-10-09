package com.leinaro.domain.ui_models

data class CharacterUiModel(
  val id: Long = -1,
  val name: String = "",
  val description: String = "",
  val thumbnailUrl: String = "",
  val landscapeUrl: String = "",
  val comics: List<ComicUiModel> = listOf(),
)

data class ComicUiModel(
  val name: String = "",
  val imageUrl: String = "",
)
