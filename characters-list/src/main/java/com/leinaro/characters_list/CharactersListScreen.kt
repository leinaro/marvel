package com.leinaro.characters_list

import android.util.Log
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.leinaro.android_architecture_tools.components.BasicPagingList
import com.leinaro.android_architecture_tools.components.SimpleItem
import com.leinaro.characters_list.ui_state.CharactersListUiState
import com.leinaro.marvel.MarvelAppBar

@Composable
fun CharactersListScreen(
  navHostController: NavHostController,
  viewModel: CharactersListViewModel = viewModel(),
) {
  Scaffold(
    backgroundColor = MaterialTheme.colors.background,
    topBar = {
      MarvelAppBar(
        onSearchClick = {
          navHostController.navigate(route = "character_search_view")
        }
      )
    },
    content = { CharactersList(navHostController, viewModel) }
  )
  viewModel.getCharacters()
}

@Composable
private fun CharactersList(
  navHostController: NavHostController,
  viewModel: CharactersListViewModel,
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
        uiState.charactersPager?.let { pagingItems ->
          val lazyPagingItems = pagingItems.collectAsLazyPagingItems()

          BasicPagingList(
            lazyPagingItems = lazyPagingItems,
            { characterUiModel ->
              SimpleItem(
                name = characterUiModel.name,
                thumbnailUrl = characterUiModel.thumbnailUrl,
                item = characterUiModel,
                onItemClick = { item ->
                  navHostController.navigate(route = "character_detail_view?id=${item.id}")
                }
              )
            },
            { characterUiModel ->
              characterUiModel.id
            },
          )
/*        BasicListView(
          pagingItems = uiState.charactersPager
        )*/
          //                  navHostController.navigate(route = "character_detail_view?id=${item.id}")
        }
      }
      else -> {
        Log.e("CharactersListScreen", "unknown ui state $uiState")
      }
    }
  }
}