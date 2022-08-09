package com.example.moviestask.viewModels

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviestask.data.models.MovieModel
import com.example.moviestask.data.models.MovieResponse
import com.example.moviestask.data.rep.MoviesRepo
import com.example.moviestask.utils.Constants
import com.example.moviestask.utils.Status
import com.runprof.usedbrands.pojo.DataResult
import com.runprof.usedbrands.pojo.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepo: MoviesRepo,
) : ViewModel() {

    /**
     * Getting user result live data
     */
    private val _moviesResult = MutableLiveData<Event<DataResult<List<MovieModel>>>>()

    val moviesResult get() : LiveData<Event<DataResult<List<MovieModel>>>> = _moviesResult


    /**
     * Get user
     */
    fun getMovies() {
        val status = _moviesResult.value?.getForcedValue()?.status
        if (status == Status.LOADING) return
        Log.i("TAG", "aaaaagetUser: 00")
        _moviesResult.value = Event(DataResult.loading())
        viewModelScope.launch {
            _moviesResult.value = Event(DataResult.loading())
            val result = moviesRepo.getMovieResponse()
            _moviesResult.value = Event(result)
            _moviesResult.value = Event(result)
        }
    }


    /**
     * Getting user result live data
     */
    private val _selectedMovie = MutableLiveData<MovieModel>()

    val selectedMovie get() : LiveData<MovieModel> = _selectedMovie

    /**
     * Get user
     */
    fun setSelectedMovie(movieModel: MovieModel) {
            _selectedMovie.value = movieModel
        }




    /**
     * Getting user result live data
     */
    private val _searchResults = MutableLiveData< List<MovieModel>?>()

    val searchResults get() : LiveData< List<MovieModel>?> = _searchResults

    /**
     * Get user
     */
    fun setsearchResults(movieModel: List<MovieModel>?) {
        _searchResults.value = movieModel
    }

}