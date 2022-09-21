package com.leinaro.characters_list.ui_components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun BasicListView() = SwipeRefresh(
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
  LazyColumn {
    items(30) { index ->
      // TODO: list items
    }
  }
}