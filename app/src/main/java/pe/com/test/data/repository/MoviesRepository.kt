package pe.com.test.data.repository

import pe.com.test.data.model.MovieBase

interface MoviesRepository {

    suspend fun getPopularMovies(apiKey: String, page: String, language: String) : MovieBase?

    suspend fun getUpcomingMovies(apiKey: String, page: String, language: String) : MovieBase?

}