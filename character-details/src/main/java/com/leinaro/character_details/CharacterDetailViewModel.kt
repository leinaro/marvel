package com.leinaro.character_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.leinaro.character_details.ui_state.CharacterDetailsUiState
import com.leinaro.core.BaseViewModel
import com.leinaro.core.Result
import com.leinaro.core.di.DefaultDispatcher
import com.leinaro.domain.usecases.GetCharacterDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    @DefaultDispatcher private val dispatchers: CoroutineDispatcher,
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
) : BaseViewModel<Result<CharacterDetailsUiState>>(Result.Loading(true)) {

    private val characterId : Long = savedStateHandle.get<Long>("id") ?: -1L

    init {
        getCharacterDetails(characterId)
    }

    private fun getCharacterDetails(characterId : Long) {
        if (characterId == -1L) {
            setValue(Result.Error(Exception("Character id could not be null"), "No se encontró el personaje"))
            return
        }
        setValue(Result.Loading(true))

        viewModelScope.launch(dispatchers) {
            getCharacterDetailsUseCase.execute(characterId)
                .onCompletion {
                    //  setValue(Result.Loading(null, false))
                }
                .catch {
                    setValue(Result.Error(null, it.message))
                }
                .collect {
                    it?.let { character ->
                        setValue(Result.Success(CharacterDetailsUiState(characterDetails = character)))
                    }
                }
        }
    }
}
