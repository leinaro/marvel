package com.leinaro.characters_list.ui_models

import com.leinaro.data.MarvelCharacter

data class CharacterUiModel(
  val id: Long,
  val name: String,
  val thumbnailUrl: String,
)

fun MarvelCharacter.toUiModel() = CharacterUiModel(
  id = id,
  name = name,
  thumbnailUrl = thumbnailUrl,
)