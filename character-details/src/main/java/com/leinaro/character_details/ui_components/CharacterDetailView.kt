package com.leinaro.character_details.ui_components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
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
import com.leinaro.android_architecture_tools.R
import com.leinaro.data.ui_models.CharacterUiModel

@Composable fun CharacterDetailView(
  characterUiModel: CharacterUiModel,
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp, 8.dp),
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
        model = characterUiModel.landscapeUrl,
        placeholder = painterResource(R.drawable.image_placeholder),
        contentDescription = null,
        modifier = Modifier
          .fillMaxWidth()
          .padding(0.dp),
        contentScale = ContentScale.Fit,
        error = painterResource(R.drawable.image_placeholder),
        onError = {
          Log.e(javaClass.name, it.result.throwable.message.orEmpty())
        }
      )
    }
  }

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp, 4.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween,
  ) {
    Text(
      text = "Name",
      style = MaterialTheme.typography.h6
    )
  }
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp, 4.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween,
  ) {
    Text(
      text = characterUiModel.name,
      style = MaterialTheme.typography.h5
    )
  }
}

@Preview @Composable fun PreviewMessageCard() {
  /*CharacterView(
    CharacterUiModel(
      1,
      "Android",
      "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
    )
  )*/
}