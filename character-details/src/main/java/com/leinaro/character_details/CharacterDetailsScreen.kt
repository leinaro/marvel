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