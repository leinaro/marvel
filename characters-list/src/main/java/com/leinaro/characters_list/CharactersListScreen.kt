package com.leinaro.characters_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.leinaro.characters_list.ui_components.BasicListView

//@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun CharactersListScreen(
  viewModel: CharactersListViewModel = viewModel()
) {
  //val uiState = viewModel.getViewData()//: CharactersListUiModel
  val state by viewModel.getViewData().collectAsState()
  BasicListView(
    state = state
  )
}