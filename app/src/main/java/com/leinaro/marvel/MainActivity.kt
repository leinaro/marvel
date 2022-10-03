package com.leinaro.marvel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.leinaro.characters_list.CharactersListScreen
import com.leinaro.marvel.ui.theme.MarvelTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MarvelTheme {
        Scaffold(
          backgroundColor = MaterialTheme.colors.background,
          topBar = { MarvelAppBar() },
          content = { CharactersListScreen() }
        )
      }
    }
  }
}

@Preview(showBackground = true) @Composable fun DefaultPreview() {
  MarvelTheme {
    CharactersListScreen()
  }
}