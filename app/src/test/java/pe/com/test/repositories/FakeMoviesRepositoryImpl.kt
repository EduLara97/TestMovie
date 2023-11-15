package pe.com.test.repositories

import pe.com.test.data.model.Movie
import pe.com.test.data.model.MovieBase
import pe.com.test.data.repository.MoviesRepository

class FakeMoviesRepositoryImpl : MoviesRepository {

    private var returnErrorCall = false

    fun setReturnErrorCall(value: Boolean) {
        returnErrorCall = value
    }

    override suspend fun getPopularMovies(
        apiKey: String,
        page: String,
        language: String
    ): MovieBase? {
        return if(returnErrorCall) {
            null
        } else {
            MovieBase(1,1,1,
                listOf(
                    Movie(
                        0.0,
                        1,
                        false,
                        "",
                        1,
                        false,
                        "",
                        "",
                        "",
                        listOf(),
                        "",
                        0.0,
                        "",
                        ""
                    ))
            )
        }
    }

    override suspend fun getUpcomingMovies(
        apiKey: String,
        page: String,
        language: String
    ): MovieBase? {
        return if(returnErrorCall) {
            null
        } else {
            MovieBase(1,1,1,
                listOf(Movie(
                    0.0,
                    1,
                    false,
                    "",
                    1,
                    false,
                    "",
                    "",
                    "",
                    listOf(),
                    "",
                    0.0,
                    "",
                    ""
                )))
        }
    }
}