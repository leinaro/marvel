package com.leinaro.characters_list.ui_components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.leinaro.android_architecture_tools.ViewDataState
import com.leinaro.characters_list.CharactersListUiModel
import com.leinaro.characters_list.ui_models.CharacterUiModel

@Composable
fun BasicListView(
  state: ViewDataState<CharactersListUiModel>?,
) = SwipeRefresh(
  state = rememberSwipeRefreshState(isRefreshing = true),
  onRefresh = { /*TODO*/ },
  indicator = { state, trigger ->
    SwipeRefreshIndicator(
      state = state,
      refreshTriggerDistance = trigger,
      contentColor = MaterialTheme.colors.onSurface
    )
  }
) {
  LazyColumn(modifier = Modifier.fillMaxHeight()) {
    items(30) { index ->
      CharacterView(characterUiModel = CharacterUiModel(0, "name", ""))
    }
  }
}