package com.leinaro.character_details.ui_components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.leinaro.android_architecture_tools.R
import com.leinaro.android_architecture_tools.theme.Gray
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

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp, 4.dp),
    verticalArrangement = Arrangement.SpaceBetween,
    horizontalAlignment = Alignment.Start,
  ) {
    Text(
      text = "Name",
      style = MaterialTheme.typography.h6
    )
    Text(
      text = characterUiModel.name,
      style = MaterialTheme.typography.h5
    )
  }
  var isExpanded by rememberSaveable { mutableStateOf(false) }

  Column {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp, 4.dp)
        .background(Gray)
        .clickable { isExpanded = !isExpanded },
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,

      ) {
      Text(
        text = "Comics (${characterUiModel.comics.size})",
        modifier = Modifier
          .weight(8f)
          .padding(8.dp),
        style = MaterialTheme.typography.h6
      )
      Icon(
        imageVector = Icons.Filled.ArrowDropDown,
        modifier = Modifier.weight(1f),
        contentDescription = null
      )
    }
    AnimatedVisibility(visible = isExpanded) {
      LazyColumn(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp, 4.dp)
      ) {
        items(characterUiModel.comics) { comic ->
          Text(text = comic.name)
        }
      }
    }

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