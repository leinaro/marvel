package com.leinaro.characters_list

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.leinaro.characters_list.ui_state.CharactersListUiState
import com.leinaro.core.BaseViewModel
import com.leinaro.core.di.DefaultDispatcher
import com.leinaro.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
  @DefaultDispatcher private val dispatchers: CoroutineDispatcher,
  private val getCharactersUseCase: GetCharactersUseCase,
) : BaseViewModel<CharactersListUiState>(CharactersListUiState()) {

  init {
    getCharacters()
  }

  fun getCharacters() {
    viewModelScope.launch(dispatchers) {
      val pager = getCharactersUseCase.execute().flow
        .catch {
          Log.e("iarl", "error $it")
        }
      setValue(CharactersListUiState(pager))
    }
  }

  fun onRefresh() {
    getCharacters()
  }
}

