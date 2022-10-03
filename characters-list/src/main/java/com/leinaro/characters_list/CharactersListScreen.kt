package com.leinaro.characters_list

import android.util.Log
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        Log.e("CharactersListScreen", "unknown ui state ")
      }
    }
  }
}