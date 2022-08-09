package com.example.moviestask.di

import com.example.moviestask.data.sources.GenresSources
import com.example.moviestask.data.sources.MoviesSources
import com.example.moviestask.utils.Constants.API
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun ProvideMovieApiService(): MoviesSources {
        return Retrofit.Builder()
            .baseUrl(API)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(MoviesSources::class.java)
    }


    @Provides
    @Singleton
    fun ProvideGenresApiService(): GenresSources {
        return Retrofit.Builder()
            .baseUrl(API)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(GenresSources::class.java)
    }
}