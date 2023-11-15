package pe.com.test.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.com.test.data.repository.MoviesRepository
import pe.com.test.data.repository.MoviesRepositoryImpl
import pe.com.test.data.source.MovieService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesMoviesRepositoryImpl(
        api: MovieService
    ) = MoviesRepositoryImpl(api) as MoviesRepository
}