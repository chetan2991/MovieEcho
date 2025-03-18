package com.chetan.movieecho.ui.feature.movies

import com.chetan.movieecho.data.model.MovieDetailDoc
import com.chetan.movieecho.data.model.TopMoviesResponse
import com.chetan.movieecho.data.model.TopMoviesResponseItem
import com.chetan.movieecho.ui.base.ViewEvent
import com.chetan.movieecho.ui.base.ViewSideEffect
import com.chetan.movieecho.ui.base.ViewState

class MovieContract {

    sealed class Event : ViewEvent {
        object Loading : Event()
        data class MovieSelected(val topMoviesResponseItem: TopMoviesResponseItem) : Event()
        object MoviesDataFetched : Event()
        object OnRetryClick : Event()
        data class RandomMovieClicked(val topMoviesResponseItem: TopMoviesResponseItem) : Event()
    }

    data class State(
        val isLoading: Boolean = false,
        val movies: List<TopMoviesResponseItem> = TopMoviesResponse(),
        val selectedMovie: TopMoviesResponseItem? = null,
        val isError: Boolean
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        object MoviesDataFetched : Effect()
        sealed class Navigation : Effect() {
            data class ToCharacterDetails(val moviesResponseItem: TopMoviesResponseItem) : Navigation()
        }
    }

}