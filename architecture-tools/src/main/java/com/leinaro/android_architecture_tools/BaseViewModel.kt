package com.leinaro.android_architecture_tools

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<T>(defaultValue: T) : ViewModel() {

  private var _uiState by mutableStateOf(defaultValue)
  private val errorViewData = MutableStateFlow<ErrorViewData?>(null)
  private val eventChannel = Channel<T>(Channel.BUFFERED)
  private val eventsFlow = eventChannel.receiveAsFlow()

  fun getUiState(): T = _uiState
  fun getErrorViewData(): StateFlow<ErrorViewData?> = errorViewData
  fun getEventsFlow(): Flow<T?> = eventsFlow

  fun setValue(value: T) {
    viewModelScope.launch {
      _uiState = value
    }
  }

  fun setErrorValue(
    value: ErrorViewData,
  ) {
    errorViewData.value = value
  }

  open fun showError(throwable: Throwable) {
    //setErrorValue(DefaultError(throwable), ShowGeneralErrorViewHandler)
  }

  suspend fun sendEvent(
    viewEvent: ViewEvent,
  ) {
    // eventChannel.send(viewEvent)
  }
}

sealed class ViewEvent {
  object NavigateToEmptyFragment : ViewEvent()
  data class ShowSnackBar(val text: String) : ViewEvent()
}