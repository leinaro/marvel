package com.leinaro.characters_list.ui_components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.leinaro.characters_list.R
import com.leinaro.characters_list.ui_models.CharacterUiModel

@Composable fun CharacterView(
  characterUiModel: CharacterUiModel,
  onItemClick: ((characterUiModel: CharacterUiModel) -> Unit) = {},
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp, 8.dp)
      .clickable { onItemClick(characterUiModel) },
    elevation = 4.dp,
    border = BorderStroke(2.dp, MaterialTheme.colors.background),
    shape = MaterialTheme.shapes.medium,
    backgroundColor = MaterialTheme.colors.surface,
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
    ) {
      AsyncImage(
        model = characterUiModel.thumbnailUrl,
        placeholder = painterResource(R.drawable.image_placeholder),
        contentDescription = null,
        modifier = Modifier
          .size(70.dp)
          .padding(0.dp),
        contentScale = ContentScale.Crop,
        error = painterResource(R.drawable.image_placeholder),
        onError = {
          Log.e("Log iarl", it.result.throwable.message.orEmpty())
        }
      )
      Column(
        modifier = Modifier
          .weight(8f)
          .padding(8.dp)
      ) {
        Text(
          text = "Name",
          style = MaterialTheme.typography.overline
        )
        Text(
          text = characterUiModel.name,
          style = MaterialTheme.typography.h5
        )
      }
      Icon(
        modifier = Modifier.weight(1f),
        painter = painterResource(id = R.drawable.chevron_right_48px),
        contentDescription = null // decorative element
      )
    }
  }
}

@Preview @Composable fun PreviewMessageCard() {
  CharacterView(
    CharacterUiModel(
      1,
      "Android",
      "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
    )
  )
}