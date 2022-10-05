package com.leinaro.data.ui_models

import com.leinaro.data.Comic
import com.leinaro.data.MarvelCharacter

data class CharacterUiModel(
  val id: Long,
  val name: String,
  val thumbnailUrl: String,
  val landscapeUrl: String,
  val comics: List<ComicUiModel>
)

data class ComicUiModel(
  val name: String,
)

fun MarvelCharacter.toUiModel() = CharacterUiModel(
  id = id,
  name = name,
  thumbnailUrl = thumbnailUrl,
  landscapeUrl = landscapeUrl,
  comics = comics.map { it.toUiModel() }
)

fun Comic.toUiModel() = ComicUiModel(
  name = name
)