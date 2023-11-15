package pe.com.test.data.api

import pe.com.test.data.model.MovieBase
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesClient {

    @GET("3/movie/popular")
    suspend fun popularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: String,
        @Query("language") language: String
    ): Response<MovieBase>

    @GET("3/movie/upcoming")
    suspend fun upcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: String,
        @Query("language") language: String
    ): Response<MovieBase>

}