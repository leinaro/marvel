package com.leinaro.characters_list

import androidx.lifecycle.viewModelScope
import com.leinaro.characters_list.ui_state.CharactersListUiState
import com.leinaro.core.BaseViewModel
import com.leinaro.core.di.DefaultDispatcher
import com.leinaro.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
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
      val pager = getCharactersUseCase.execute().flow
      setValue(CharactersListUiState.ShowCharactersListUiState(pager))
    }
  }

  fun onRefresh() {
    getCharacters()
  }
}

