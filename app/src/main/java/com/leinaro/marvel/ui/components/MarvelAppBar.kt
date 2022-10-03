package com.leinaro.marvel

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.leinaro.marvel.ui.theme.MarvelTheme

@Composable
fun MarvelAppBar(
  onSearchClick: () -> Unit = {},
) {
  TopAppBar(
    title = { Text("Marvel") },
    navigationIcon = {
      IconButton(onClick = { /* doSomething() */ }) {
        Icon(Icons.Filled.Menu, contentDescription = null)
      }
    },
    actions = {
      // RowScope here, so these icons will be placed horizontally
      IconButton(onClick = { onSearchClick() }) {
        Icon(Icons.Default.Search, contentDescription = "Buscar")
      }
      IconButton(onClick = { /* doSomething() */ }) {
        Icon(Icons.Default.Info, contentDescription = "Informacion")
      }
    }
  )
}

@Preview(showBackground = true)
@Composable
private fun MarvelAppBarPreview() {
  MarvelTheme {
    MarvelAppBar()
  }
}