package com.leinaro.domain.ui_models

import com.leinaro.data.data.ComicData
import com.leinaro.data.data.MarvelCharacterData

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

fun MarvelCharacterData.toUiModel() = CharacterUiModel(
  id = id,
  name = name,
  description = description,
  thumbnailUrl = thumbnailUrl,
  landscapeUrl = landscapeUrl,
  comics = comics.toUiModel()
)

fun ComicData.toUiModel() = ComicUiModel(
  name = name,
  imageUrl = imageUrl
)

fun List<ComicData>.toUiModel() = map { it.toUiModel() }
