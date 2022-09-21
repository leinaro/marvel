package com.leinaro.characters_list

import androidx.lifecycle.viewModelScope
import com.leinaro.android_architecture_tools.BaseViewModel
import com.leinaro.characters_list.ui_models.CharacterUiModel
import com.leinaro.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
  private val getCharactersUseCase: GetCharactersUseCase,
) : BaseViewModel<CharactersListUiModel>() {

  fun onViewCreated() {
    viewModelScope.launch(Dispatchers.IO) {
      getCharactersUseCase.execute().collect{ characteres ->
        /*setValue(
          CharactersListUiModel.ShowCharactersList(characteres = characteres.toUIModel())
        )*/
      }
    }
  }
}

sealed class CharactersListUiModel {
  data class ShowCharactersList(val characteres: List<CharacterUiModel>): CharactersListUiModel()

}

sealed class BasicListEvent {

}