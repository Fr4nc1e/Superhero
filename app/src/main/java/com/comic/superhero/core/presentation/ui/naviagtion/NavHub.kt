package com.comic.superhero.core.presentation.ui.naviagtion

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.comic.superhero.core.presentation.ui.naviagtion.destination.Destination
import com.comic.superhero.feature.detail.DetailScreen
import com.comic.superhero.feature.favorite.FavoriteScreen
import com.comic.superhero.feature.home.presentation.ui.HomeScreen
import com.comic.superhero.feature.search.presentaion.ui.SearchScreen

@Composable
fun NavHub(
    modifier: Modifier,
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    NavHost(
        navController = navHostController,
        startDestination = Destination.HomeScreen.route
    ) {
        composable(Destination.HomeScreen.route) {
            HomeScreen(
                modifier = modifier,
                snackbarHostState = snackbarHostState
            )
        }
        composable(Destination.SearchScreen.route) {
            SearchScreen(
                modifier = modifier,
                snackbarHostState = snackbarHostState
            )
        }
        composable(Destination.FavoriteScreen.route) {
            FavoriteScreen(
                modifier = modifier,
                snackbarHostState = snackbarHostState
            )
        }
        composable(
            route = Destination.DetailScreen.route + "/{heroId}",
            arguments = listOf(
                navArgument("heroId") {
                    type = NavType.StringType
                }
            )
        ) {
            DetailScreen(
                modifier = modifier,
                snackbarHostState = snackbarHostState
            )
        }
    }
}
