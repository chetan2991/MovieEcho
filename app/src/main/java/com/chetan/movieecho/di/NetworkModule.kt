package com.chetan.movieecho.di

import com.chetan.movieecho.data.MoviesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    @Provides
    @Singleton
    fun provideOkHttpClient( loggingInterceptor: HttpLoggingInterceptor) : OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://imdb236.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMoviesApi(retrofit: Retrofit) : MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }
}