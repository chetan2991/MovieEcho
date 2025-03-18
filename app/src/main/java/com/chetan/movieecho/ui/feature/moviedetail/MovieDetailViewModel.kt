package com.chetan.movieecho.ui.feature.moviedetail

import androidx.lifecycle.ViewModel
import com.chetan.movieecho.ui.base.UViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor() : ViewModel(),
    UViewModel<MovieDetailContract.State, MovieDetailContract.Events, MovieDetailContract.Effect> {
    private val _state = MutableStateFlow(
        MovieDetailContract.State(
            isLoading = true,
            movieDetail = null,
            isError = false
        )
    )
    override val state: StateFlow<MovieDetailContract.State> = _state.asStateFlow()
    private val _effect = MutableSharedFlow<MovieDetailContract.Effect>()
    override val effect = _effect.asSharedFlow()
    override fun event(event: MovieDetailContract.Events) {
            when(event){
                MovieDetailContract.Events.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
                is MovieDetailContract.Events.OnFavoriteClicked -> {
                    //TODO
                }

                is MovieDetailContract.Events.MovieDetailsLoaded ->{
                    _state.value = _state.value.copy(isLoading = false, movieDetail = event.movieDetail)
                }
            }
    }
}