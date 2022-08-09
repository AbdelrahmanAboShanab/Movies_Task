package com.example.moviestask.data.sources

import com.example.moviestask.data.models.GenresModel
import com.example.moviestask.data.models.MovieResponse
import com.example.moviestask.utils.Constants.GENRES_ENDPOINT
import com.example.moviestask.utils.Constants.MOVIES_ENDPOINT
import dagger.Provides
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton

interface MoviesSources {

    @GET(MOVIES_ENDPOINT)
    suspend fun getMoviesResponse(): Response<MovieResponse>

}