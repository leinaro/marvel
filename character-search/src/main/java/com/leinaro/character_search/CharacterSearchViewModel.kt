package com.leinaro.character_search

import androidx.lifecycle.viewModelScope
import androidx.paging.map
import com.leinaro.android_architecture_tools.BaseViewModel
import com.leinaro.android_architecture_tools.di.DefaultDispatcher
import com.leinaro.character_search.ui_state.CharactersSearchUiState
import com.leinaro.data.ui_models.CharacterUiModel
import com.leinaro.data.ui_models.toUiModel
import com.leinaro.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterSearchViewModel @Inject constructor(
  @DefaultDispatcher private val dispatchers: CoroutineDispatcher,
  private val getCharactersUseCase: GetCharactersUseCase,
) : BaseViewModel<CharactersSearchUiState>(CharactersSearchUiState.DefaultState) {

  //  private var allCharacters: ArrayList<CharacterUiModel> = ArrayList<CharacterUiModel>()
  private val searchText: MutableStateFlow<String> = MutableStateFlow("")
  private var showProgressBar: MutableStateFlow<Boolean> = MutableStateFlow(false)
  private var matchedCharacters: MutableStateFlow<List<CharacterUiModel>> =
    MutableStateFlow(arrayListOf())

  val userSearchModelState = combine(
    searchText,
    matchedCharacters,
    showProgressBar
  ) { searchText, matchedCharacters, showProgress ->

    CharactersSearchUiState.SearchCharactersUiState(
      searchText = searchText,
      matchedCharacters = matchedCharacters,
      showProgress = showProgress
    )
  }

  fun getCharacters(nameStartsWith: String) {
    viewModelScope.launch(dispatchers) {
      val pager = getCharactersUseCase.execute(nameStartsWith).flow.map { pagingData ->
        pagingData.map { marvelCharacter ->
          marvelCharacter.toUiModel()
        }
      }
      //matchedCharacters.value = pager
      setValue(CharactersSearchUiState.ShowCharactersListUiState(pager))
    }
  }

  fun onSearchTextChanged(changedSearchText: String) {
    searchText.value = changedSearchText
    if (changedSearchText.isEmpty()) {
      // matchedUsers.value = arrayListOf()
      return
    }
    getCharacters(changedSearchText)
/*    val usersFromSearch = allUsers.filter { x ->
      x.username.contains(changedSearchText, true) ||
          x.email.contains(changedSearchText, true) || x.name.contains(
        changedSearchText,
        true
      )
    }*/
    //matchedUsers.value = emptyList()//usersFromSearch
  }

  fun onClearClick() {
    searchText.value = ""
    //  matchedUsers.value = arrayListOf()
  }
/*
  fun onRefresh() {
    getCharacters()
  }*/
}
/*
fun MarvelCharacter.toUiModel() = CharacterUiModel(
  id = id,
  name = name,
  thumbnailUrl = thumbnailUrl,
)*/

