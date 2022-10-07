package com.leinaro.marvel

import androidx.compose.ui.test.junit4.createComposeRule
import com.leinaro.core.theme.MarvelTheme
import org.junit.Rule
import org.junit.Test

class AppNavHostKtTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun myTest() {
    // Start the app
    composeTestRule.setContent {
      MarvelTheme {
        // AppNavHost(navController, scaffoldState)
        //   CharactersListScreen(uiState = fakeUiState, /*...*/)
      }
    }

    // composeTestRule.onNodeWithText("Continue").performClick()

    //  composeTestRule.onNodeWithText("Welcome").assertIsDisplayed()
  }
}