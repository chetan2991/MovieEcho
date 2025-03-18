package com.chetan.movieecho.di

import com.chetan.movieecho.data.MoviesRepository
import com.chetan.movieecho.data.MoviesRepositoryImpl
import com.chetan.movieecho.domain.GetMoviesUseCase
import com.chetan.movieecho.domain.GetMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    @Provides
    @Singleton
    fun provideMoviesRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository {
        return moviesRepositoryImpl
    }
   @Provides
   @Singleton
   fun provideGetMoviesUseCase(getMoviesUseCaseImpl: GetMoviesUseCaseImpl): GetMoviesUseCase {
       return getMoviesUseCaseImpl

   }
}