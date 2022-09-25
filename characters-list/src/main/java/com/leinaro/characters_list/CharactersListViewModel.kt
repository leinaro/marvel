package com.leinaro.characters_list

import androidx.lifecycle.viewModelScope
import com.leinaro.android_architecture_tools.BaseViewModel
import com.leinaro.android_architecture_tools.di.DefaultDispatcher
import com.leinaro.characters_list.ui_models.CharacterUiModel
import com.leinaro.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface CharactersListUiState {
  object DefaultState : CharactersListUiState

  data class ShowCharactersListUiState(
    val characters: List<CharacterUiModel> = emptyList(),
  ) : CharactersListUiState
}

@HiltViewModel
class CharactersListViewModel @Inject constructor(
  @DefaultDispatcher private val dispatchers: CoroutineDispatcher,
  private val getCharactersUseCase: GetCharactersUseCase,
) : BaseViewModel<CharactersListUiState>(CharactersListUiState.DefaultState) {

  init {
    getCharacters()
  }

  fun getCharacters() {
    viewModelScope.launch(dispatchers) {
      getCharactersUseCase.execute()
        .collect { characters ->
          val value = CharactersListUiState.ShowCharactersListUiState(
            characters = characters.map {
              CharacterUiModel(it.id, it.name, it.thumbnailUrl)
            })
          setValue(value)
        }
    }
  }

  fun onRefresh() {
    getCharacters()
  }
}