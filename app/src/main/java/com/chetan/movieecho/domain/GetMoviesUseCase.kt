package com.chetan.movieecho.domain

import com.chetan.movieecho.common.ApiResult
import com.chetan.movieecho.data.model.TopMoviesResponse
import kotlinx.coroutines.flow.Flow

interface GetMoviesUseCase {
    suspend operator fun invoke(): Flow<ApiResult<TopMoviesResponse>> // Replace 'Any' with your data type
}