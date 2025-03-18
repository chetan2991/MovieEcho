package com.chetan.movieecho.ui.feature.movies.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.chetan.movieecho.data.model.buildTopMoviesResponseItemPreview
import com.chetan.movieecho.ui.base.SIDE_EFFECTS_KEY
import com.chetan.movieecho.ui.feature.movies.MovieContract
import com.chetan.movieecho.ui.feature.movies.MovieList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    state : MovieContract.State,
    effectFlow: Flow<MovieContract.Effect>? = null,
    onEventSent: (event: MovieContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: MovieContract.Effect.Navigation) -> Unit
){

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach {
            effect ->
            when(effect){
                is MovieContract.Effect.Navigation.ToCharacterDetails -> onNavigationRequested(effect)
                is MovieContract.Effect.MoviesDataFetched -> {
                    onEventSent(MovieContract.Event.MoviesDataFetched)
                }
            }
        }?.collectLatest{}
    }
    Scaffold(
        topBar = {
            MovieEchoTopBar()
        }
    ) {
        innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            when{
                state.isLoading -> {
                   // LoadingIndicator()
                    MovieList(
                        movies = state.movies,
                        onMovieClick = {

                        }
                    )
                }
                state.isError -> {
                    MovieList(
                        movies = state.movies,
                        onMovieClick = {

                        }
                    )

                }
                else ->{
                    MovieList(
                        movies = state.movies,
                        onMovieClick = {

                        }
                    )

                }

            }
        }
    }
}
@Preview( showBackground = true)
@Composable
fun MovieScreenPreview(){
   val movies = List(4){
       buildTopMoviesResponseItemPreview()
   }
    MoviesScreen(
        state = MovieContract.State(
            isLoading = false,
            movies = movies,
            selectedMovie = null,
            isError = false
        ),
        onEventSent = {},
        effectFlow = null,
        onNavigationRequested = {}
    )
}