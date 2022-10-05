package com.leinaro.marvel

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.leinaro.android_architecture_tools.theme.MarvelTheme
import com.leinaro.characters_list.R
import com.leinaro.characters_list.ui_components.InfoDialog

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun MarvelAppBar(
  onSearchClick: () -> Unit = {},
) {
  var showInfoDialog by remember { mutableStateOf(false) }

  TopAppBar(
    title = { Text("Marvel") },
    navigationIcon = {
      IconButton(onClick = { /* doSomething() */ }) {
        Icon(Icons.Filled.Menu, contentDescription = null)
      }
    },
    actions = {
      IconButton(onClick = { onSearchClick() }) {
        Icon(Icons.Default.Search, contentDescription = stringResource(R.string.search))
      }
      IconButton(onClick = { showInfoDialog = !showInfoDialog }) {
        Icon(Icons.Default.Info, contentDescription = stringResource(R.string.information))
      }
    }
  )
  if (showInfoDialog) {
    InfoDialog(onDismissRequest = { showInfoDialog = !showInfoDialog })
  }
}

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
private fun MarvelAppBarPreview() {
  MarvelTheme {
    MarvelAppBar()
  }
}