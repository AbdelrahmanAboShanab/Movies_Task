package com.example.moviestask.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.moviestask.data.models.MovieModel
import com.example.moviestask.databinding.FragmentSearchBinding
import com.example.moviestask.ui.adapters.MoviesAdapter
import com.example.moviestask.utils.Status
import com.example.moviestask.viewModels.MoviesViewModel
import com.runprof.usedbrands.ui.exts.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding


    private var mRootLoaded: Boolean = false

    /**
     *  Movies View Model
     */
    private val mMoviesViewModel by activityViewModels<MoviesViewModel>()

    private lateinit var moviesAdapter: MoviesAdapter


    private var allMovies: MutableList<MovieModel>? = mutableListOf()

    private var specificMovies: MutableList<MovieModel>? = mutableListOf()


    private lateinit var watcher: TextWatcher

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        if (!this::binding.isInitialized) {
            binding = FragmentSearchBinding.inflate(inflater, container, false)

        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!mRootLoaded) {
            setupViews()
            mRootLoaded = true
        }
        observerViewModel()

    }

    private fun observerViewModel() {
        mMoviesViewModel.moviesResult.observeEvent(viewLifecycleOwner) { result ->
            when (result.status) {
                Status.LOADING -> {
                }
                Status.LOADING_MORE -> {
                    Log.i("TAG", "observerViewModel: loading")
                }
                Status.SUCCESS -> {
                    allMovies = result.data as MutableList<MovieModel>?
                }
                else -> {
                }
            }
        }

        mMoviesViewModel.searchResults.observe(viewLifecycleOwner) { movies ->
            handleMoviesResult(movies)
        }
    }
    private fun handleMoviesResult(data: List<MovieModel>?) {
        moviesAdapter = MoviesAdapter(
            data!!,
            { movie ->
            }
        )
        moviesAdapter.addItems(data)
        binding.recyclerView.adapter = moviesAdapter
    }

    private fun setupViews() {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Search"
        mMoviesViewModel.getMovies()
        watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if(s.toString().isNotEmpty()){
                    performSearchForAllMovie(s.toString())
                }
            }
        }
    }
    override fun onPause() {
        super.onPause()
        binding.etSearch.removeTextChangedListener(watcher)
    }

    override fun onResume() {
        super.onResume()
        binding.etSearch.addTextChangedListener(watcher)
    }
    //i don't know the search applicable for all movies or current set of movies so i did two functions (performSearchForAllMovie -performSearchForCurrentList)
    private fun performSearchForAllMovie(movie: String) {
        specificMovies?.clear()
        allMovies?.map { it ->
            if (it.title.lowercase().startsWith(movie.lowercase())) {
                specificMovies?.add(it)
            }
        }
        handleSearchResult(specificMovies)
    }


    private fun handleSearchResult(data: List<MovieModel>?) {
        moviesAdapter = MoviesAdapter(
            data!!,
            { movie ->
            }
        )
        moviesAdapter.addItems(data)
        binding.recyclerView.adapter = moviesAdapter
    }

//    private fun performSearchForCurrentList(movie: String) {
//        searchMovies?.clear()
//        specificMovies?.map { it ->
//            if (it.title.lowercase().startsWith(movie.lowercase())) {
//                searchMovies?.add(it)
//            }
//        }
//        handleSearchResult(searchMovies)
//    }
}

