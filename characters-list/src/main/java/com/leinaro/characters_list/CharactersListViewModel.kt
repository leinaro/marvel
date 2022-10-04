package com.leinaro.characters_list

import androidx.lifecycle.viewModelScope
import androidx.paging.map
import com.leinaro.android_architecture_tools.BaseViewModel
import com.leinaro.android_architecture_tools.di.DefaultDispatcher
import com.leinaro.data.ui_models.toUiModel
import com.leinaro.characters_list.ui_state.CharactersListUiState
import com.leinaro.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

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
      val pager = getCharactersUseCase.execute().flow.map { pagingData ->
        pagingData.map { marvelCharacter ->
          marvelCharacter.toUiModel()
        }
      }
      setValue(CharactersListUiState.ShowCharactersListUiState(pager))
    }
  }

  fun onRefresh() {
    getCharacters()
  }
}

