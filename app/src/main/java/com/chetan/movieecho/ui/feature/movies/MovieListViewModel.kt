package com.chetan.movieecho.ui.feature.movies
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.movieecho.domain.GetMoviesUseCase
import com.chetan.movieecho.ui.base.UViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import com.chetan.movieecho.common.ApiResult
import com.chetan.movieecho.data.model.TopMoviesResponseItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel(), UViewModel<MovieContract.State, MovieContract.Event, MovieContract.Effect> {

//    private val _state: MutableState<MovieContract.State> = mutableStateOf(MovieContract.State(
//        isLoading = false,
//        movies = emptyList(),
//        selectedMovie = null,
//        isError = false
//    ))
    private val _state = MutableStateFlow(MovieContract.State(
        isLoading = false,
        movies = emptyList(),
        selectedMovie = null,
        isError = false
    ))
    override val state: StateFlow<MovieContract.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<MovieContract.Effect>()
    override val effect = _effect.asSharedFlow()

    init {
        getMovies()
    }

    override fun event(event: MovieContract.Event) {
        when (event) {
            is MovieContract.Event.MoviesDataFetched -> {
                _state.value = _state.value.copy(isLoading = false, isError = false)
            }

            is MovieContract.Event.MovieSelected -> {
                viewModelScope.launch {
                    _effect.emit(MovieContract.Effect.Navigation.ToCharacterDetails(event.topMoviesResponseItem))
                }
            }

            MovieContract.Event.Loading -> {
                _state.value = _state.value.copy(isLoading = true, isError = false)

            }
            is MovieContract.Event.RandomMovieClicked -> {
                viewModelScope.launch {
                    _effect.emit(MovieContract.Effect.Navigation.ToCharacterDetails(event.topMoviesResponseItem))
                }
            }

            is MovieContract.Event.OnRetryClick -> {
                getMovies()
            }
        }
    }

    private fun getMovies() {
        viewModelScope.launch {
            getMoviesUseCase().onEach { apiResult ->
                when (apiResult) {
                    is ApiResult.Error -> {
                        _state.value = _state.value.copy(isLoading = false, isError = true)
                    }

                    is ApiResult.Loading -> {
                        _state.value = _state.value.copy(isLoading = true, isError = false)
                    }

                    is ApiResult.Success -> {
                        _state.value = _state.value.copy(isLoading = false, isError = false ,movies = apiResult.data as List<TopMoviesResponseItem>)
                    }
                }
            }.catch {
                _state.value = _state.value.copy(isLoading = false, isError = true)
            }.collect {}
        }
    }
}