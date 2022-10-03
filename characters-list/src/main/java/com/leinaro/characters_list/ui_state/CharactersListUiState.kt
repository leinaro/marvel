package com.leinaro.characters_list.ui_state

import androidx.paging.PagingData
import com.leinaro.characters_list.ui_models.CharacterUiModel
import kotlinx.coroutines.flow.Flow

sealed class CharactersListUiState(val loadingView: Boolean = false) {
  object DefaultState : CharactersListUiState(false)

  data class ShowCharactersListUiState(
    val charactersPager: Flow<PagingData<CharacterUiModel>>? = null,
    val loading: Boolean = false,
  ) : CharactersListUiState(loading)
}