package com.chetan.movieecho.data.model

class TopMoviesResponse : ArrayList<TopMoviesResponseItem>()

data class TopMoviesResponseItem(
    val averageRating: Double,
    val budget: Long,
    val contentRating: String,
    val countriesOfOrigin: List<String>,
    val description: String,
    val endYear: Any,
    val externalLinks: List<String>,
    val filmingLocations: List<String>,
    val genres: List<String>,
    val grossWorldwide: Long,
    val id: String,
    val interests: List<String>,
    val isAdult: Boolean,
    val numVotes: Int,
    val originalTitle: String,
    val primaryImage: String,
    val primaryTitle: String,
    val productionCompanies: List<ProductionCompany>,
    val releaseDate: String,
    val runtimeMinutes: Int,
    val spokenLanguages: List<String>,
    val startYear: Int,
    val type: String,
    val url: String
)

data class ProductionCompany(
    val id: String,
    val name: String
)

fun buildTopMoviesResponseItemPreview() = TopMoviesResponseItem(
    2.2,2L,"good", listOf("US"),"description","", listOf("")
, listOf(""), listOf(""),2L,"123", listOf(""),true,2,"GoodFellas"
,"GoodFellas","GoodFellas", listOf(ProductionCompany("123","GoodFellas")),
    "",120, listOf(""),2000,"good","good"
    )