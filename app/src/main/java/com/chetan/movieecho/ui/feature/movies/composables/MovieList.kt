package com.chetan.movieecho.ui.feature.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.chetan.movieecho.R
import com.chetan.movieecho.data.model.TopMoviesResponseItem
import com.chetan.movieecho.ui.feature.movies.composables.MovieListItem

@Composable
fun MovieList(
    movies: List<TopMoviesResponseItem>,
    onMovieClick: (TopMoviesResponseItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {

        items(movies) { movie ->
            MovieListItem(
                topMoviesResponseItem = movie,
                onMovieClick = onMovieClick
            )

        }
    }

}