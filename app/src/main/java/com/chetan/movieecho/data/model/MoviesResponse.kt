package com.chetan.movieecho.data.model

data class MoviesResponse(
    val movieDetailDocs: List<MovieDetailDoc>,
    val limit: Int,
    val offset: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)

data class MovieDetailDoc(
    val _id: String,
    val academyAwardNominations: Int,
    val academyAwardWins: Int,
    val boxOfficeRevenueInMillions: Double,
    val budgetInMillions: Int,
    val name: String,
    val rottenTomatoesScore: Double,
    val runtimeInMinutes: Int
)
fun buildMovieDocPreview() = MovieDetailDoc(
    "123",3,1,
    3.0,1,
    "lord of ring",4.5,120)
