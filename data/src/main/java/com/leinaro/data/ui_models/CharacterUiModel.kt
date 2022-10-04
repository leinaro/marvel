package com.leinaro.data.ui_models

import com.leinaro.data.MarvelCharacter

data class CharacterUiModel(
  val id: Long,
  val name: String,
  val thumbnailUrl: String,
  val landscapeUrl: String,
)

fun MarvelCharacter.toUiModel() = CharacterUiModel(
  id = id,
  name = name,
  thumbnailUrl = thumbnailUrl,
  landscapeUrl = landscapeUrl
)