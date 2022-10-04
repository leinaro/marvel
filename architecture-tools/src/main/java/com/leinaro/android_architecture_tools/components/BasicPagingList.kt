package com.leinaro.android_architecture_tools.components

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
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items

@Composable
fun <T : Any> BasicPagingList(
  lazyPagingItems: LazyPagingItems<T>,
  itemContent: @Composable (T) -> Unit,
  getId: (item: T) -> Long,
) {
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
    items(items = lazyPagingItems, key = { item -> getId(item) }) { item ->
      if (item != null) {
        itemContent(item)
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