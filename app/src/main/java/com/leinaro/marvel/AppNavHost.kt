package com.leinaro.marvel

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.leinaro.character_details.CharacterDetailViewModel
import com.leinaro.character_details.CharacterDetailsScreen
import com.leinaro.character_search.CharacterSearchScreen
import com.leinaro.character_search.CharacterSearchViewModel
import com.leinaro.characters_list.CharactersListScreen
import com.leinaro.characters_list.CharactersListViewModel
import com.leinaro.core.components.MarvelAppBar
import com.leinaro.core.components.MarvelAppBarData

enum class NavPath(
  val route: String,
) {
  CharactersList(route = "characters_list"),
  CharacterSearchView(route = "character_search_view"),
  CharacterDetailView(route = "character_detail_view"),
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun AppNavHost(
  navHostController: NavHostController, scaffoldState: ScaffoldState
) {

  var marvelAppBarData: MarvelAppBarData by remember { mutableStateOf(MarvelAppBarData.Default) }

  Scaffold(
    scaffoldState = scaffoldState,
    backgroundColor = MaterialTheme.colors.background,
    topBar = {
      MarvelAppBar(
        data = marvelAppBarData,
        onSearchClick = { navHostController.navigate(route = NavPath.CharacterSearchView.route) },
      )
    },
    content = { padding ->
      NavHost(
        modifier = Modifier.padding(padding),
        navController = navHostController,
        startDestination = NavPath.CharactersList.route
      ) {

        composable(NavPath.CharactersList.route) {
          val viewModel = hiltViewModel<CharactersListViewModel>()
          marvelAppBarData = MarvelAppBarData.Default
          CharactersListScreen(
            viewModel = viewModel,
            navigateTo = { route -> navHostController.navigate(route = route) }
          )
        }

        composable(NavPath.CharacterSearchView.route) {
          val viewModel = hiltViewModel<CharacterSearchViewModel>()
          marvelAppBarData = MarvelAppBarData.None
          CharacterSearchScreen(
            navHostController,
            viewModel,
          )
        }

        composable(
          "${NavPath.CharacterDetailView.route}?id={id}", arguments = listOf(
            navArgument("id") {
              type = NavType.LongType
            })
        ) {
          val viewModel = hiltViewModel<CharacterDetailViewModel>()
          marvelAppBarData = MarvelAppBarData.CanGoBack(onBackClick = {
            navHostController.popBackStack()
          })

          CharacterDetailsScreen(
            marvelAppBarData = marvelAppBarData,
            viewModel = viewModel
          )
        }
      }
    }
  )

}