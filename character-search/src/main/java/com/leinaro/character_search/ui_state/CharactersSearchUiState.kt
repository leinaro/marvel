package com.leinaro.character_search.ui_state

import androidx.paging.PagingData
import com.leinaro.domain.ui_models.CharacterUiModel
import kotlinx.coroutines.flow.Flow

sealed class CharactersSearchUiState() {
  object DefaultState : CharactersSearchUiState()

  data class SearchCharactersUiState(
    val searchText: String,
    val matchedCharacters: List<CharacterUiModel>,
    val showProgress: Boolean
  ) : CharactersSearchUiState()

  data class ShowCharactersListUiState(
    val charactersPager: Flow<PagingData<CharacterUiModel>>? = null,
    val loading: Boolean = false,
  ) : CharactersSearchUiState()
}