package com.chetan.movieecho.data

import com.chetan.movieecho.data.model.TopMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
//GET https://imdb236.p.rapidapi.com/imdb/top250-movies HTTP/1.1
//Accept: application/json
//Content-Type: null
//x-rapidapi-ua: RapidAPI-Playground
//x-rapidapi-key: aad6789e62mshc044984c1b853d4p17a480jsnece1953b3330
//x-rapidapi-host: imdb236.p.rapidapi.com
//specificMethodHeaders: [object Object]

interface MoviesApi {
   @Headers(
      "x-rapidapi-key: aad6789e62mshc044984c1b853d4p17a480jsnece1953b3330",
      "x-rapidapi-host: imdb236.p.rapidapi.com"

   )
   @GET("imdb/top250-movies")
   suspend fun getTop250Movies(): TopMoviesResponse
}