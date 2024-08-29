package com.samkt.shared.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.samkt.shared.di.AppContainer
import com.samkt.shared.presentation.categoryScreen.CategoryScreen
import com.samkt.shared.presentation.categoryScreen.CategoryViewModel
import com.samkt.shared.presentation.gameScreen.GameScreen
import com.samkt.shared.presentation.gameScreen.GameViewModel
import com.samkt.shared.presentation.homeScreen.HomeScreen
import com.samkt.shared.presentation.homeScreen.HomeViewModel
import com.samkt.shared.presentation.searchScreen.SearchScreen
import com.samkt.shared.presentation.searchScreen.SearchViewModel


sealed class AppRoute(val route: String) {
    data object Home : AppRoute("home")
    data object GameScreen : AppRoute("game")
    data object SearchScreen : AppRoute("search")
    data object CategoryScreen : AppRoute("category")
}


@Composable
fun AppNavigationGraph(
    appContainer: AppContainer
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppRoute.Home.route) {
        destination(route = AppRoute.Home.route) {
            HomeScreen(
                navController = navController,
                viewModel = viewModel<HomeViewModel> {
                    HomeViewModel(
                        appContainer.gameRepository()
                    )
                }
            )
        }

        destination(route = AppRoute.CategoryScreen.route + "?category={category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                }
            )) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category")!!
            CategoryScreen(
                navController = navController,
                viewModel = viewModel<CategoryViewModel> {
                    CategoryViewModel(
                        appContainer.gameRepository()
                    )
                },
                category = category
            )
        }

        destination(route = AppRoute.GameScreen.route + "?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")!!
            GameScreen(
                id = id,
                viewModel = viewModel<GameViewModel> {
                    GameViewModel(
                        appContainer.gameRepository()
                    )
                },
                navController = navController
            )
        }

        destination(
            route = AppRoute.SearchScreen.route
        ) { _ ->
            SearchScreen(
                navController = navController,
                viewModel = viewModel<SearchViewModel> {
                    SearchViewModel(
                        appContainer.gameRepository()
                    )
                }
            )
        }
    }
}