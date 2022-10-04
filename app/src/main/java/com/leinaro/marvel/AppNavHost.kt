package com.leinaro.marvel

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.leinaro.character_search.CharacterSearchScreen
import com.leinaro.character_search.CharacterSearchViewModel
import com.leinaro.characters_list.CharactersListScreen
import com.leinaro.characters_list.CharactersListViewModel

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
  NavHost(
    navController = navHostController,
    startDestination = NavPath.CharactersList.route
  ) {

    composable(NavPath.CharactersList.route) {
      val viewModel = hiltViewModel<CharactersListViewModel>()
      CharactersListScreen(
        viewModel = viewModel,
        onSearchClick = { navHostController.navigate(NavPath.CharacterSearchView.route) }
      )
    }

    composable(NavPath.CharacterSearchView.route) {
      val viewModel = hiltViewModel<CharacterSearchViewModel>()
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
/*val userDetailViewModel = hiltViewModel<UserDetailViewModel>()
      UserDetailUI(
        navHostController = navHostController,
        userDetailViewModel = userDetailViewModel
      )*/
    }
  }
}