package com.leinaro.characters_list

import android.util.Log
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.leinaro.characters_list.ui_components.BasicListView
import com.leinaro.characters_list.ui_state.CharactersListUiState
import com.leinaro.marvel.MarvelAppBar

@Composable
fun CharactersListScreen(
  viewModel: CharactersListViewModel = viewModel(),
  onSearchClick: () -> Unit = {},
) {
  Scaffold(
    backgroundColor = MaterialTheme.colors.background,
    topBar = {
      MarvelAppBar(
        onSearchClick = onSearchClick
      )
    },
    content = { CharactersList(viewModel) }
  )
}

@Composable
private fun CharactersList(viewModel: CharactersListViewModel) {
  val uiState: CharactersListUiState = viewModel.getUiState()
  SwipeRefresh(
    state = rememberSwipeRefreshState(isRefreshing = uiState.loadingView),
    onRefresh = { viewModel.onRefresh() },
    indicator = { state, trigger ->
      SwipeRefreshIndicator(
        state = state,
        refreshTriggerDistance = trigger,
        contentColor = MaterialTheme.colors.primary
      )
    }
  ) {
    when (uiState) {
      is CharactersListUiState.ShowCharactersListUiState -> {
        BasicListView(pagingItems = uiState.charactersPager)
      }
      else -> {
        Log.e("CharactersListScreen", "unknown ui state $uiState")
      }
    }
  }
}