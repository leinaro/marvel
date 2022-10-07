package com.leinaro.core.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import com.leinaro.core.components.topappbar.BackIconButton
import com.leinaro.core.components.topappbar.InfoIconButton
import com.leinaro.core.components.topappbar.SearchIconButon
import com.leinaro.core.components.topappbar.SearchTextField
import com.leinaro.core.theme.MarvelTheme

sealed class MarvelAppBarData(var title: String = "Marvel") {
  object None : MarvelAppBarData()
  object Default : MarvelAppBarData()
  data class CanGoBack(
    val onBackClick: () -> Unit,
  ) : MarvelAppBarData()

  data class SearchBar(
    val onSearchTextChanged: (String) -> Unit = {},
    val onBackClick: () -> Unit = {},
  ) : MarvelAppBarData("")
}

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun MarvelAppBar(
  data: MarvelAppBarData = MarvelAppBarData.Default,
  onSearchClick: () -> Unit = {},
) {
  var showInfoDialog by remember { mutableStateOf(false) }
  if (data != MarvelAppBarData.None) {
    TopAppBar(
      title = { Text(data.title) },
      navigationIcon = { NavigationIconByType(data) },
      actions = {
        ActionsByType(
          data = data,
          onInfoClick = { showInfoDialog = !showInfoDialog },
          onSearchClick = { onSearchClick() })
      }
    )
  }

  if (showInfoDialog) {
    InfoDialog(onDismissRequest = { showInfoDialog = !showInfoDialog })
  }
}

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun ActionsByType(
  data: MarvelAppBarData,
  onSearchClick: () -> Unit = {},
  onInfoClick: () -> Unit = {},
  searchText: String = "",
  placeholderText: String = "",
  onSearchTextChanged: (String) -> Unit = {},
  onClearClick: () -> Unit = {},
) {
  when (data) {
    is MarvelAppBarData.None -> {}
    is MarvelAppBarData.SearchBar -> {
      SearchTextField(
        searchText, placeholderText, onSearchTextChanged, onClearClick
      )
    }
    is MarvelAppBarData.CanGoBack -> {
      InfoIconButton(onClick = { onInfoClick() })
    }
    MarvelAppBarData.Default -> {
      SearchIconButon(onClick = { onSearchClick() })
      InfoIconButton(onClick = { onInfoClick() })
    }
  }
}

@Composable
fun NavigationIconByType(data: MarvelAppBarData) {
  when (data) {
    is MarvelAppBarData.SearchBar -> {
      BackIconButton(onClick = { data.onBackClick() })
    }
    is MarvelAppBarData.CanGoBack -> {
      BackIconButton(onClick = { data.onBackClick() })
    }
    is MarvelAppBarData.Default -> {}
    is MarvelAppBarData.None -> {}
  }
}

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
private fun MarvelAppBarPreview() {
  MarvelTheme {
    MarvelAppBar(data = MarvelAppBarData.SearchBar())
  }
}