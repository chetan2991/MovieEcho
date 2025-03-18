package com.chetan.movieecho.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chetan.movieecho.data.model.TopMoviesResponseItem
import com.google.gson.Gson
import java.net.URLDecoder

@Composable
fun MovieEchoAppNavigation(){

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination =  Navigation.Routes.MOVIES
    ){
        composable(
            route = Navigation.Routes.MOVIES
        ){
            MovieScreenDestination(navController)
        }
        composable(
            route = "${Navigation.Routes.MOVIE_DETAILS}/{movieJson}"
        ){ backStackEntry ->
            val movieJson = backStackEntry.arguments?.getString("movieJson")
            val movie = movieJson?.let { Gson().fromJson(URLDecoder.decode(it, "UTF-8"), TopMoviesResponseItem::class.java) }
            MovieDetailScreenDestination(navController,movie!!)
        }
    }
}
object Navigation {
    object Args {

    }
    object Routes {
        const val MOVIES = "movies"
        const val MOVIE_DETAILS = "movie_details"
    }
}