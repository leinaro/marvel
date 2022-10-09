package com.leinaro.characters_list.ui_state

import androidx.paging.PagingData
import com.leinaro.domain.ui_models.CharacterUiModel
import kotlinx.coroutines.flow.Flow

data class CharactersListUiState(
    val charactersPager: Flow<PagingData<CharacterUiModel>>? = null,
    val loading: Boolean = false,
)