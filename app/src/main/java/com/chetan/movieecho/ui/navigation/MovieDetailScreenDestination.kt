package com.chetan.movieecho.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.chetan.movieecho.data.model.TopMoviesResponseItem
import com.chetan.movieecho.ui.feature.moviedetail.MovieDetailContract
import com.chetan.movieecho.ui.feature.moviedetail.composable.MovieDetailScreen

@Composable
fun MovieDetailScreenDestination(navController: NavController, topMoviesResponseItem: TopMoviesResponseItem){
    MovieDetailScreen(
        topMoviesResponseItem = topMoviesResponseItem,
        onNavigationRequested = { navigationEffect ->
            when(navigationEffect){
                MovieDetailContract.Effect.Navigation.OnBackClicked -> {
                    navController.popBackStack()
                }
            }
        }
    )
}