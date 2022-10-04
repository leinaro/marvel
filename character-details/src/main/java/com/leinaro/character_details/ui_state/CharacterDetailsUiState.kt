package com.leinaro.character_details.ui_state

import com.leinaro.data.ui_models.CharacterUiModel

sealed class CharacterDetailsUiState(val loadingView: Boolean = false) {
  object DefaultState : CharacterDetailsUiState(false)

  data class ShowCharacterDetailsUiState(
    val characterDetails: CharacterUiModel,
    val loading: Boolean = false,
  ) : CharacterDetailsUiState(loading)
}
