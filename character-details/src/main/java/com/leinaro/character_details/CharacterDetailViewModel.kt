package com.leinaro.character_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.leinaro.android_architecture_tools.BaseViewModel
import com.leinaro.android_architecture_tools.di.DefaultDispatcher
import com.leinaro.character_details.ui_state.CharacterDetailsUiState
import com.leinaro.data.ui_models.toUiModel
import com.leinaro.domain.domain_status.ApiResponse
import com.leinaro.domain.usecases.GetCharacterDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
  @DefaultDispatcher private val dispatchers: CoroutineDispatcher,
  private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
) : BaseViewModel<CharacterDetailsUiState>(CharacterDetailsUiState.DefaultState) {

  private val characterId = savedStateHandle.get<Long>("id")

  fun getCharacterDetails() {
    if (characterId == null) {
      return
    }
    viewModelScope.launch(dispatchers) {
      getCharacterDetailsUseCase.execute(characterId)
        .collect {
          when (it) {
            is ApiResponse.Success -> {
              it.data?.let { character ->
                val value = CharacterDetailsUiState.ShowCharacterDetailsUiState(
                  characterDetails = character.toUiModel()
                )
                setValue(value)
              }
            }
            is ApiResponse.Error -> {
            }
            is ApiResponse.Loading -> {
            }
          }
        }
    }
  }

/*
  fun onRefresh() {
    getCharacters()
  }*/
}
