package com.leinaro.characters_list

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.leinaro.characters_list.ui_components.BasicListView

@Composable
fun CharactersListScreen(
  viewModel: CharactersListViewModel = viewModel()
) {

  val uiState: CharactersListUiState = viewModel.getUiState()

  SwipeRefresh(
    state = rememberSwipeRefreshState(isRefreshing = false),
    onRefresh = { viewModel.onRefresh() },
    indicator = { state, trigger ->
      SwipeRefreshIndicator(
        state = state,
        refreshTriggerDistance = trigger,
        contentColor = MaterialTheme.colors.onSurface
      )
    }
  ) {
    when (uiState) {
      is CharactersListUiState.ShowCharactersListUiState -> {
        BasicListView(characters = uiState.characters)
      }
      else -> {}
    }
  }
}