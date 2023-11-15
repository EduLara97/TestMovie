package pe.com.test.data.repository

import pe.com.test.data.model.MovieBase
import pe.com.test.data.source.MovieService
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val api: MovieService
) : MoviesRepository {
    override suspend fun getPopularMovies(apiKey: String, page: String, language: String) : MovieBase? {
        return api.getMoviePopular(apiKey, page, language)
    }

    override suspend fun getUpcomingMovies(apiKey: String, page: String, language: String) : MovieBase? {
        return api.getUpcomingMovies(apiKey, page, language)
    }
}