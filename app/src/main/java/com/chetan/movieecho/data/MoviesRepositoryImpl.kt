package com.chetan.movieecho.data

import com.chetan.movieecho.data.model.TopMoviesResponse
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi
) : MoviesRepository {
    override suspend fun getTop250MoviesList(): TopMoviesResponse {
        return moviesApi.getTop250Movies()
    }
}