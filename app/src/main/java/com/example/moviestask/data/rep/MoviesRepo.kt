package com.example.moviestask.data.rep

import android.content.ContentValues.TAG
import android.util.Log
import com.example.moviestask.data.dao.MoviesDao
import com.example.moviestask.data.models.MovieModel
import com.example.moviestask.data.models.MovieResponse
import com.example.moviestask.data.sources.MoviesSources
import com.runprof.usedbrands.pojo.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class MoviesRepo @Inject constructor(private var movieSource: MoviesSources){

    suspend fun getMovieResponse(): DataResult<List<MovieModel>> =
    withContext(Dispatchers.IO) {

        var v = movieSource.getMoviesResponse()
        var resukt = DataResult.success(v.body()?.results)
        Log.i(TAG, "getMovieResponse: "+v.body()?.results?.size)
        return@withContext resukt
    }

//
//    fun insertMovies(list: List<MovieModel?>?) {
//        movieDao.InsertMovies(list)
//    }
//
//    fun getMovies(): LiveData<List<MovieModel?>?>? {
//        return movieDao.GetMovies()
//    }
}