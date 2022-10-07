package com.leinaro.character_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.leinaro.character_details.ui_state.CharacterDetailsUiState
import com.leinaro.core.BaseViewModel
import com.leinaro.core.di.DefaultDispatcher
import com.leinaro.domain.usecases.GetCharacterDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class Result<out R>(val status: ApiStatus, val data: R?, val message: String?) {
  data class Success<out T>(val _data: T?) : Result<T>(
    status = ApiStatus.Success,
    data = _data,
    message = null,
  )

  data class Error(val exception: Exception?, val _message: String?) : Result<Nothing>(
    status = ApiStatus.Error,
    data = null,
    message = _message,
  )

  data class Loading<out R>(val isLoading: Boolean) : Result<R>(
    status = ApiStatus.Loading,
    data = null,
    message = null
  )
}

sealed class ApiStatus {
  object Success : ApiStatus()
  object Error : ApiStatus()
  object Loading : ApiStatus()
}

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
  @DefaultDispatcher private val dispatchers: CoroutineDispatcher,
  private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
) : BaseViewModel<Result<CharacterDetailsUiState>>(Result.Loading(true)) {

  private val characterId = savedStateHandle.get<Long>("id")

  init {
    getCharacterDetails()
  }

  fun getCharacterDetails() {
    if (characterId == null) {
      return
    }
    setValue(Result.Loading(true))

    viewModelScope.launch(dispatchers) {
      getCharacterDetailsUseCase.execute(characterId)
        .onCompletion {
          //  setValue(Result.Loading(null, false))
        }
        .catch {
          //uiState =
        }
        .collect {
          it?.let { character ->
            setValue(Result.Success(CharacterDetailsUiState(characterDetails = character)))
          }
          /*when (it) {
            is Resource.Success -> {
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
          }*/
        }
    }
  }
}
