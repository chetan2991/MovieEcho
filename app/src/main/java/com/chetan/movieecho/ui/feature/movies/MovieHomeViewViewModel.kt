package com.chetan.movieecho.ui.feature.movies

import androidx.compose.animation.core.copy
import androidx.lifecycle.viewModelScope
import com.chetan.movieecho.common.ApiResult
import com.chetan.movieecho.data.model.TopMoviesResponse
import com.chetan.movieecho.domain.GetMoviesUseCase
import com.chetan.movieecho.domain.GetMoviesUseCaseImpl
import com.chetan.movieecho.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Benefits of Using an GetMoviesUseCase Interface
 * Testability:
 * Mocking: Interfaces make it much easier to mock dependencies in your unit tests. You can create a mock implementation of the use case interface and inject it into your ViewModel for testing. This allows you to isolate the ViewModel's logic and test it independently of the actual use case implementation.
 * Test Doubles: Interfaces allow you to use test doubles (mocks, stubs, spies) to control the behavior of the use case in your tests.
 * Flexibility and Decoupling:
 * Loose Coupling: Interfaces decouple the ViewModel from the concrete implementation of the use case. The ViewModel only depends on the interface, not the specific class.
 * Swappable Implementations: You can easily swap out different implementations of the use case without modifying the ViewModel. This is useful for:
 * Different Data Sources: You might have different use case implementations that fetch data from a network, a local database, or a mock data source.
 * Different Business Logic: You might have different use case implementations for different scenarios (e.g., a "lite" version of the app vs. a "full" version).
 * Clean Architecture:
 * Dependency Inversion: Using interfaces aligns with the Dependency Inversion Principle (DIP) from SOLID and Clean Architecture. High-level modules (like the ViewModel) should not depend on low-level modules (like the concrete use case implementation). They should both depend on abstractions (interfaces).
 * Maintainability:
 * Easier Refactoring: If you need to refactor the use case implementation, you can do so without affecting the ViewModel, as long as the interface remains the same
 */
@HiltViewModel
class MovieHomeViewViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : BaseViewModel<MovieContract.Event, MovieContract.State, MovieContract.Effect>() {

    init {
        getMovies()
    }

    override fun setInitialState() = MovieContract.State(
        isLoading = false,
        movies = TopMoviesResponse(),
        selectedMovie = null,
        isError = false
    )

    override fun handleEvents(event: MovieContract.Event) {
        when (event) {
            is MovieContract.Event.Loading -> {
                setState {
                    copy(isLoading = true)
                }
            }

            is MovieContract.Event.MovieSelected -> {
                setEffect {
                    MovieContract.Effect.Navigation.ToCharacterDetails(event.topMoviesResponseItem)
                }
            }

            is MovieContract.Event.MoviesDataFetched -> {
                //
            }

            is MovieContract.Event.RandomMovieClicked -> TODO()
            MovieContract.Event.OnRetryClick -> TODO()
        }
    }

    fun getMovies() {
        viewModelScope.launch {
           getMoviesUseCase().onEach { apiResult ->
                when (apiResult) {
                    is ApiResult.Error -> {
                        setState { copy(isLoading = false, isError = true) }
                    }

                    is ApiResult.Loading -> {
                        setState { copy(isLoading = true, movies = movies) }
                    }
                    is ApiResult.Success -> {
                        setState { copy(isLoading = false, movies = apiResult.data ?: TopMoviesResponse()) }
                    }
                }
            }.catch {
                setState { copy(isLoading = false, isError = true) }
           }.collect()
        }
    }

}

