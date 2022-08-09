package com.example.moviestask.data.rep

import android.content.ContentValues.TAG
import android.util.Log
import com.example.moviestask.data.dao.MoviesDao
import com.example.moviestask.data.models.GenresModel
import com.example.moviestask.data.models.MovieModel
import com.example.moviestask.data.models.MovieResponse
import com.example.moviestask.data.sources.GenresSources
import com.example.moviestask.data.sources.MoviesSources
import com.runprof.usedbrands.pojo.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class GenresRepo @Inject constructor(private var genresSource: GenresSources) {

    suspend fun getGenresResponse(): DataResult<List<GenresModel>?> =
        withContext(Dispatchers.IO) {

            var response = genresSource.getGenresResponse()
            var result :MutableList<GenresModel> = mutableListOf()
            result.add(GenresModel(0,"All Movies"))
            response.body()?.genres?.let { result.addAll(it) }
            var finalResult : List<GenresModel> = listOf()
            finalResult = result.toList()

            return@withContext DataResult.success(finalResult)
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