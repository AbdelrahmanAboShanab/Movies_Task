package com.example.moviestask.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviestask.data.models.GenresModel
import com.example.moviestask.data.models.MovieModel
import com.example.moviestask.data.models.MovieResponse
import com.example.moviestask.data.rep.GenresRepo
import com.example.moviestask.data.rep.MoviesRepo
import com.example.moviestask.utils.Constants
import com.example.moviestask.utils.Status
import com.runprof.usedbrands.pojo.DataResult
import com.runprof.usedbrands.pojo.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val genresRepo: GenresRepo,
) : ViewModel() {

    /**
     * Getting user result live data
     */
    private val _genresResult = MutableLiveData<Event<DataResult<List<GenresModel>?>>>()

    val genresResult get() : LiveData<Event<DataResult<List<GenresModel>?>>> = _genresResult

    /**
     * Get user
     */
    fun getGenres() {
        val status = _genresResult.value?.getForcedValue()?.status
        if (status == Status.LOADING) return
        Log.i("TAG", "aaaaagetUser: 00")
        _genresResult.value = Event(DataResult.loading())
        viewModelScope.launch {
            _genresResult.value = Event(DataResult.loading())
            val result = genresRepo.getGenresResponse()
            _genresResult.value = Event(result)
            _genresResult.value = Event(result)
        }
    }
}