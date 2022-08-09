package com.example.moviestask.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.moviestask.data.models.GenresModel
import com.example.moviestask.data.models.MovieModel
import com.example.moviestask.databinding.FragmentHomeBinding
import com.example.moviestask.ui.adapters.MoviesAdapter
import com.example.moviestask.ui.adapters.TabsAdapter
import com.example.moviestask.utils.Status
import com.example.moviestask.viewModels.GenresViewModel
import com.example.moviestask.viewModels.MoviesViewModel
import com.runprof.usedbrands.ui.exts.observeEvent
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding


    /**
     *  Movies View Model
     */
    private val mMoviesViewModel by activityViewModels<MoviesViewModel>()


    /**
     *  Genres View Model
     */
    private val mGenresViewModel by viewModels<GenresViewModel>()


    /**
     * Indicates whether the view was loaded before or not
     */
    private var mRootLoaded: Boolean = false

    private lateinit var tabsAdapter: TabsAdapter
    private lateinit var moviesAdapter: MoviesAdapter

    private var allMovies: MutableList<MovieModel>? = mutableListOf()

    private  var specificMovies: MutableList<MovieModel>? = mutableListOf()

    private  var searchMovies: MutableList<MovieModel>? = mutableListOf()

    private lateinit var watcher: TextWatcher

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        if (!this::binding.isInitialized) {
            binding = FragmentHomeBinding.inflate(inflater, container, false)

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!mRootLoaded) {
            setupViews()
            //   showShimmer()
            mRootLoaded = true
        }
        observerViewModel()
    }


    private fun setupViews() {
        mMoviesViewModel.getMovies()
        mGenresViewModel.getGenres()


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

        allMovies?.map { it ->
            if (it.title.lowercase().startsWith(movie.lowercase())) {
                specificMovies?.add(it)
            }
        }
        handleSearchResult(specificMovies)
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


    private fun observerViewModel() {
        mMoviesViewModel.moviesResult.observeEvent(viewLifecycleOwner) { result ->
            when (result.status) {
                Status.LOADING -> {
                }
                Status.LOADING_MORE -> {
                }
                Status.SUCCESS -> {
                    handleMoviesResult(result.data)
                    allMovies = result.data as MutableList<MovieModel>?
                }
                else -> {
                }
            }
        }

        mGenresViewModel.genresResult.observeEvent(viewLifecycleOwner) { result ->
            when (result.status) {
                Status.LOADING -> {
                }
                Status.LOADING_MORE -> {
                    Log.i("TAG", "observerViewModel: loading")
                }
                Status.SUCCESS -> {
                    handleGenresResult(result.data)
                }
                else -> {

                }
            }
        }
    }

    private fun handleGenresResult(data: List<GenresModel>?) {

        tabsAdapter = TabsAdapter(
            data!!.toList(),
            { tab, view ->
                //  handleGenresResult(data)
                specificMovies?.clear()
                allMovies?.map { it ->
                    if (tab.id == 0) {
                        handleMoviesResult(allMovies)
                    } else {
                        if (it.genre_ids.contains(tab.id)) {
                            Log.i("TAG", "setData:4 ")
                            specificMovies?.add(it)
                        }
                        //     view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.dim_gray))
                        handleMoviesResult(specificMovies)

                    }
                }

            }
        )
        tabsAdapter.addItems(specificMovies!!.toList())
        binding.rvTabs.adapter = tabsAdapter
        // tabsAdapter.hasMore() = true
        tabsAdapter.notifyItemRangeChanged(0, specificMovies!!.size)
    }

    private fun handleMoviesResult(data: List<MovieModel>?) {
        moviesAdapter = MoviesAdapter(
            data!!,
            { movie ->
                mMoviesViewModel.setSelectedMovie(movie)
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment())
            }
        )
        moviesAdapter.addItems(data)
        binding.recyclerView.adapter = moviesAdapter

    }

    private fun handleSearchResult(data: List<MovieModel>?) {
        Log.i("TAG", "afterTextChanged:5555 ")
        mMoviesViewModel.setsearchResults(data)
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())

    }
}