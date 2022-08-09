package com.example.moviestask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.moviestask.databinding.FragmentMovieDetailsBinding
import com.example.moviestask.viewModels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding


    private var mRootLoaded: Boolean = false

    /**
     *  Movies View Model
     */
    private val mMoviesViewModel by activityViewModels<MoviesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        if (!this::binding.isInitialized) {
            binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!mRootLoaded) {
            //   showShimmer()
            mRootLoaded = true
        }
        observerViewModel()

    }

    private fun observerViewModel() {
        var s = mMoviesViewModel.selectedMovie.value
        mMoviesViewModel.selectedMovie.observe(viewLifecycleOwner) { movie ->
            (activity as AppCompatActivity?)!!.supportActionBar!!.title = movie.title
            Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500" + movie.poster_path)
                .into(binding.ivMovie)
            binding.tvName.text = movie.title
            binding.tvYearOfProduction.text = movie.release_date
            binding.rbRating.rating = movie.vote_average.toFloat() / 2
            binding.descreption.text = movie.overview
        }
    }

}