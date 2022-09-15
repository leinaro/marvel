package com.leinaro.characters_list.ui_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    //border = BorderStroke(2.dp, ),
    shape = MaterialTheme.shapes.medium,
    backgroundColor = MaterialTheme.colors.surface,
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
    ) {
      Box(
        modifier = Modifier
          .clip(RoundedCornerShape(4.dp))
          //.shadow()
          .wrapContentHeight()
          .wrapContentWidth()
      ) {
        /* GlideImage(
           imageModel = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg", // loading a network image using an URL.
           imageOptions = ImageOptions(
             contentScale = ContentScale.Inside,
             alignment = Alignment.Center
           ),
           modifier = Modifier.aspectRatio(0.8f),
           previewPlaceholder = R.drawable.gray_background
         )*/
      }
      Column(
        modifier = Modifier
          .weight(1f)
      ) {
        Text(text = "Name ${characterUiModel.name}")
      }
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