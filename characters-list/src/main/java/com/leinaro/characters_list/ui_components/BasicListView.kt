package com.leinaro.characters_list.ui_components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.leinaro.characters_list.ui_models.CharacterUiModel

@Composable
fun BasicListView(
  characters: List<CharacterUiModel>,
) = LazyColumn(
  modifier = Modifier.fillMaxHeight()
) {
  items(characters) { item ->
    CharacterView(
      characterUiModel = item,
      onItemClick = {}
    )
  }
}