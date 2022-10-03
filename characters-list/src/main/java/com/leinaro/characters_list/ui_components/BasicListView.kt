package com.leinaro.characters_list.ui_components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.leinaro.characters_list.ui_models.CharacterUiModel
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BasicListView(
  pagingItems: Flow<PagingData<CharacterUiModel>>?,
) {
  pagingItems?.let {
    val lazyPagingItems = pagingItems.collectAsLazyPagingItems()
    LazyColumn(
      modifier = Modifier.fillMaxHeight()
    ) {
      if (lazyPagingItems.loadState.refresh == LoadState.Loading) {
        item {
          Column(
            modifier = Modifier.fillParentMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
          ) {
            CircularProgressIndicator()
          }
        }
      }
      items(items = lazyPagingItems, key = { character -> character.id }) { character ->
        if (character != null) {
          CharacterView(
            characterUiModel = character,
            onItemClick = {}
          )
        } else {
          CircularProgressIndicator()
        }
      }
      if (lazyPagingItems.loadState.append == LoadState.Loading) {
        item {
          CircularProgressIndicator(
            modifier = Modifier
              .fillMaxWidth()
              .wrapContentWidth(Alignment.CenterHorizontally)
          )
        }
      }
    }
  }
/*  items(characters) { item ->
    item?.let {
      CharacterView(
        characterUiModel = it,
        onItemClick = {}
      )
    }
  }*/
}