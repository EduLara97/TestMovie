package pe.com.test.data.source

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.com.test.data.api.MoviesClient
import pe.com.test.data.model.MovieBase
import javax.inject.Inject

class MovieService @Inject constructor(
    private val moviesClient: MoviesClient
) {
    suspend fun getMoviePopular(apiKey: String, page: String, language: String) : MovieBase? =
        withContext(Dispatchers.IO) {
            try {
                val response = moviesClient.popularMovies(apiKey, page, language)
                response.body()
            } catch (e: Exception) {
                null
            }

        }

    suspend fun getUpcomingMovies(apiKey: String, page: String, language: String) : MovieBase? =
        withContext(Dispatchers.IO) {
            try {
                val response = moviesClient.upcomingMovies(apiKey, page, language)
                response.body()
            } catch (e: Exception) {
                null
            }
        }

}