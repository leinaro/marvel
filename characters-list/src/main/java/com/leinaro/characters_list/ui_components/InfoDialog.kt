package com.leinaro.characters_list.ui_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leinaro.android_architecture_tools.theme.MarvelTheme
import com.leinaro.characters_list.R

@Composable
fun InfoDialog(
  onDismissRequest: () -> Unit = {}
) {
  AlertDialog(
    onDismissRequest = onDismissRequest,
    title = {
      Text(text = "Marvel")
    },
    text = {
      Column {
        Text("Marvel application by Inés Rojas")
      }
    },
    buttons = {
      Row(
        modifier = Modifier.padding(all = 8.dp),
        horizontalArrangement = Arrangement.Center
      ) {
        Button(
          modifier = Modifier.fillMaxWidth(),
          onClick = onDismissRequest
        ) {
          Text(stringResource(R.string.close))
        }
      }
    }
  )
}

@Preview(showBackground = true)
@Composable
private fun InfoDialogPreview() {
  MarvelTheme {
    InfoDialog()
  }
}