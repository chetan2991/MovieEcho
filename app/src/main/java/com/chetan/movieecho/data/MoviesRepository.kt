package com.chetan.movieecho.data

import com.chetan.movieecho.data.model.TopMoviesResponse

interface MoviesRepository {
    suspend fun getTop250MoviesList() : TopMoviesResponse
}