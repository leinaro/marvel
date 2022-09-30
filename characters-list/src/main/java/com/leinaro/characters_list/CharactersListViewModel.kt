package com.leinaro.characters_list

import androidx.lifecycle.viewModelScope
import com.leinaro.android_architecture_tools.BaseViewModel
import com.leinaro.android_architecture_tools.di.DefaultDispatcher
import com.leinaro.characters_list.ui_models.CharacterUiModel
import com.leinaro.domain.ApiResponse
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
        .collect {
          println("iarl ---- " + it)
          when (it) {
            is ApiResponse.Success -> {
              //characters ->
              val characters = it.data.orEmpty()
              val value = CharactersListUiState.ShowCharactersListUiState(
                characters = characters.map {
                  CharacterUiModel(it.id, it.name.orEmpty(), it.thumbnailUrl.orEmpty())
                })
              setValue(value)
            }
            is ApiResponse.Error -> {
              println("iarl ---- " + it._message)
            }
            is ApiResponse.Loading -> {

            }
          }
        }
    }
  }

  fun onRefresh() {
    getCharacters()
  }
}