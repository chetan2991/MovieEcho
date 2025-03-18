package com.chetan.movieecho.ui.feature.movies.composables


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.chetan.movieecho.data.model.buildTopMoviesResponseItemPreview
import com.chetan.movieecho.ui.base.SIDE_EFFECTS_KEY
import com.chetan.movieecho.ui.base.collectInLaunchedEffect
import com.chetan.movieecho.ui.base.use
import com.chetan.movieecho.ui.feature.common.MovieEchoError
import com.chetan.movieecho.ui.feature.common.MovieEchoProgressIndicator
import com.chetan.movieecho.ui.feature.movies.MovieContract
import com.chetan.movieecho.ui.feature.movies.MovieList
import com.chetan.movieecho.ui.feature.movies.MovieListViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesListScreen(
    viewModel: MovieListViewModel = hiltViewModel(),
    onNavigationRequested: (navigationEffect: MovieContract.Effect.Navigation) -> Unit
) {
    val (state, event, effect) = use(viewModel)

    effect.collectInLaunchedEffect { effect ->
        when (effect) {
            is MovieContract.Effect.Navigation -> onNavigationRequested(effect)
            is MovieContract.Effect.MoviesDataFetched -> event(MovieContract.Event.MoviesDataFetched)
        }
    }

    Scaffold(
        topBar = {
            MovieEchoTopBar()
        }
        ,
        floatingActionButton = {
            RandomMovieFloatingActionButton(
                onRandomMovieClick = {
                    if (state.movies.isNotEmpty()) {
                        event.invoke(MovieContract.Event.RandomMovieClicked(state.movies.random()))
                    }
                }
            )
        },
        bottomBar = {
            MovieEchoBottomBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                state.isLoading -> {
                    MovieEchoProgressIndicator()
                }

                state.isError -> {
                    MovieEchoError(
                        {
                            event(MovieContract.Event.OnRetryClick)
                        }
                    )
                }

                else -> {
                    MovieList(
                        movies = state.movies,
                        onMovieClick = { topMoviesResponseItem ->
                            event(MovieContract.Event.MovieSelected(topMoviesResponseItem))
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieListScreenPreview() {
    val movies = List(4) {
        buildTopMoviesResponseItemPreview()
    }
    MoviesListScreen(
        onNavigationRequested = {}
    )
}