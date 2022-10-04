package com.leinaro.character_details

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.leinaro.character_details.ui_components.CharacterDetailTopAppBar
import com.leinaro.character_details.ui_components.CharacterDetailView
import com.leinaro.character_details.ui_state.CharacterDetailsUiState

@Composable
fun CharacterDetailsScreen(
  navHostController: NavHostController,
  viewModel: CharacterDetailViewModel = viewModel()
) {
  val uiState: CharacterDetailsUiState = viewModel.getUiState()

  Column(modifier = Modifier.fillMaxSize()) {
    when (uiState) {
      is CharacterDetailsUiState.ShowCharacterDetailsUiState -> {
        CharacterDetailTopAppBar(
          title = uiState.characterDetails.name,
          onBackClick = {
            navHostController.popBackStack()
          }
        )
        CharacterDetailView(uiState.characterDetails)
      }
      else -> {
        Log.e("CharacterDetailsScreen", "unknown ui state ")
      }
    }
  }
  viewModel.getCharacterDetails()
}

/*
* package com.leinaro.characters_list

import android.util.Log
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
    Log.e(object {}.javaClass.enclosingMethod?.name, "unknown ui state ")

    when (uiState) {
      is CharactersListUiState.ShowCharactersListUiState -> {
        BasicListView(characters = uiState.characters)
      }
      else -> {
        Log.e(object {}.javaClass.enclosingMethod?.name, "unknown ui state ")
      }
    }
  }
}*/