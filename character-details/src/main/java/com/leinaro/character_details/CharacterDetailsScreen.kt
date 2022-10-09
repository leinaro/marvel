package com.leinaro.character_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.leinaro.character_details.ui_components.CharacterDetailView
import com.leinaro.core.Result
import com.leinaro.core.components.InfoDialog
import com.leinaro.core.components.MarvelAppBarData

@Composable
fun CharacterDetailsScreen(
  marvelAppBarData: MarvelAppBarData,
  viewModel: CharacterDetailViewModel = viewModel(),
  navigateBack: () -> Unit = {},
) {
  val uiState = viewModel.uiState

  when (uiState) {
    is Result.Loading -> {
      if (uiState.isLoading) {
        Column(
          modifier = Modifier.fillMaxSize(),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally,
        ) {
          CircularProgressIndicator()
        }
      }
    }
    is Result.Success -> {
      Column(modifier = Modifier.fillMaxSize()) {
        uiState.data?.characterDetails?.let {
          marvelAppBarData.title = it.name
          CharacterDetailView(it)
        }
      }
    }
    is Result.Error -> {
        InfoDialog(
          title = "Error",
          message = uiState.message.orEmpty(),
          onDismissRequest = { navigateBack() }
        )
    }
  }
}