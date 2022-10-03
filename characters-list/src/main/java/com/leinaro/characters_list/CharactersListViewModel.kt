package com.leinaro.characters_list

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.leinaro.android_architecture_tools.BaseViewModel
import com.leinaro.android_architecture_tools.di.DefaultDispatcher
import com.leinaro.characters_list.ui_models.CharacterUiModel
import com.leinaro.data.MarvelCharacter
import com.leinaro.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CharactersListUiState(val loadingView: Boolean = false) {
  object DefaultState : CharactersListUiState(false)

  data class ShowCharactersListUiState(
    val charactersPager: Flow<PagingData<CharacterUiModel>>? = null,
    val loading: Boolean = false,
  ) : CharactersListUiState(loading)
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

fun MarvelCharacter.toUiModel() = CharacterUiModel(
  id = id,
  name = name,
  thumbnailUrl = thumbnailUrl,
)

