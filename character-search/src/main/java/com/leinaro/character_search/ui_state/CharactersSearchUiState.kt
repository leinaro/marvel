package com.leinaro.character_search.ui_state

import androidx.paging.PagingData
import com.leinaro.data.ui_models.CharacterUiModel
import kotlinx.coroutines.flow.Flow

sealed class CharactersSearchUiState(val loadingView: Boolean = false) {
  object DefaultState : CharactersSearchUiState(false)

  data class SearchCharactersUiState(
    val searchText: String,
    val matchedCharacters: List<CharacterUiModel>,
    val showProgress: Boolean
  ) : CharactersSearchUiState(false)

  data class ShowCharactersListUiState(
    val charactersPager: Flow<PagingData<CharacterUiModel>>? = null,
    val loading: Boolean = false,
  ) : CharactersSearchUiState(loading)
}