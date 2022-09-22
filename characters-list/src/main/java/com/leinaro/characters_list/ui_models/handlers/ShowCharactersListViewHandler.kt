package com.leinaro.characters_list.ui_models.handlers

import com.leinaro.android_architecture_tools.BaseViewModel
import com.leinaro.android_architecture_tools.ViewHandler
import com.leinaro.characters_list.CharactersListUiModel
import com.leinaro.characters_list.ui_models.CharacterUiModel

object ShowCharactersListViewHandler: ViewHandler<CharactersListUiModel.ShowCharactersList, BaseViewModel<CharactersListUiModel>> {
  override fun CharactersListUiModel.ShowCharactersList.perform(
    context: Any,
    viewModel: BaseViewModel<CharactersListUiModel>
  ) {
  }
}