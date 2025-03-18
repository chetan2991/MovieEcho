package com.chetan.movieecho.ui.feature.moviedetail

import com.chetan.movieecho.data.model.TopMoviesResponseItem
import com.chetan.movieecho.ui.base.ViewEvent

class MovieDetailContract {
    sealed class Events : ViewEvent {
        object Loading : Events()
        data class OnFavoriteClicked(val movieId: String) : Events()
        data class MovieDetailsLoaded(val movieDetail: TopMoviesResponseItem) : Events()
    }
    data class State(
        val isLoading: Boolean = false,
        val movieDetail: TopMoviesResponseItem? = null,
        val isError: Boolean
    )
    sealed class Effect{
        data class onFavoriteClicked(val movieId: String) : Effect()
        sealed class Navigation : Effect() {
            object OnBackClicked : Navigation()
        }
    }
}