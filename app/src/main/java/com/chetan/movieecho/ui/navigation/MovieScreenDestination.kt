package com.chetan.movieecho.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chetan.movieecho.ui.feature.movies.MovieContract
import com.chetan.movieecho.ui.feature.movies.MovieHomeViewViewModel
import com.chetan.movieecho.ui.feature.movies.composables.MoviesListScreen
import com.chetan.movieecho.ui.feature.movies.composables.MoviesScreen
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun MovieScreenDestination( navController: NavController){
//    val viewModel = hiltViewModel<MovieHomeViewViewModel>()
//    MoviesScreen(
//        state = viewModel.viewState.value,
//        effectFlow = viewModel.effect,
//        onEventSent = { event -> viewModel.setEvent(event) },
//        onNavigationRequested = { navigationEffect ->
//            if (navigationEffect is MovieContract.Effect.Navigation.ToCharacterDetails) {
//                navController.navigate("details/${navigationEffect.movieId}")
//            }
//        }
//
//    )

    MoviesListScreen(
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                is MovieContract.Effect.Navigation.ToCharacterDetails -> {
                    navigationEffect.moviesResponseItem
                    val movieJson = URLEncoder.encode(Gson().toJson(navigationEffect.moviesResponseItem), StandardCharsets.UTF_8.toString())
                    navController.navigate("${Navigation.Routes.MOVIE_DETAILS}/$movieJson")
                }
            }
        }

    )

}