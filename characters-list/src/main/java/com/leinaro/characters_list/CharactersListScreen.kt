package com.leinaro.characters_list

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.leinaro.characters_list.ui_state.CharactersListUiState
import com.leinaro.core.components.BasicPagingList
import com.leinaro.core.components.SimpleItem

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun CharactersListScreen(
  viewModel: CharactersListViewModel = viewModel(),
  navigateTo: (route: String) -> Unit,
) {
  CharactersList(
    viewModel,
    navigateTo,
  )
}

@Composable
fun CharactersList(
  viewModel: CharactersListViewModel,
  navigateTo: (route: String) -> Unit = {},
  onRefresh: () -> Unit = {},
) {
  val uiState: CharactersListUiState = viewModel.uiState

  SwipeRefresh(
    state = rememberSwipeRefreshState(isRefreshing = uiState.loading),
    onRefresh = { onRefresh() },
    indicator = { state, trigger ->
      SwipeRefreshIndicator(
        state = state,
        refreshTriggerDistance = trigger,
        contentColor = MaterialTheme.colors.primary
      )
    }
  ) {
    uiState.charactersPager?.let { pagingItems ->
      val lazyPagingItems = pagingItems.collectAsLazyPagingItems()
      BasicPagingList(
        lazyPagingItems = lazyPagingItems,
        { characterUiModel ->
          SimpleItem(
            name = characterUiModel.name,
            thumbnailUrl = characterUiModel.thumbnailUrl,
            item = characterUiModel,
            onItemClick = { item -> navigateTo("character_detail_view?id=${item.id}") }
          )
        },
        { characterUiModel ->
          characterUiModel.id
        },
      )
    }
  }
}