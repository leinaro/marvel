package com.leinaro.character_details.ui_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CharacterDetailTopAppBar(title: String, onBackClick: () -> Unit) {
  TopAppBar(
    title = {
      Text(title, textAlign = TextAlign.Center)
    },
    modifier = Modifier.fillMaxWidth(),
    navigationIcon = {
      IconButton(onClick = { onBackClick() }) {
        Icon(
          imageVector = Icons.Filled.ArrowBack,
          modifier = Modifier,
          contentDescription = "Volver"
        )
      }
    }
  )
}