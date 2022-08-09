package com.example.moviestask.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.moviestask.data.models.GenresModel
import com.example.moviestask.data.models.MovieModel
import com.example.moviestask.databinding.MovieItemBinding

class MoviesAdapter(
    private val listOfMovies: List<MovieModel>,
    private val onClick: (MovieModel) -> Unit,
    ) :
    BaseItemAdapter<MovieModel>() {


    override fun getItemCount(): Int = listOfMovies.size

    class MovieViewHolder(binding: MovieItemBinding) : MyViewHolder(binding.root) {
        private val mImage: ImageView = binding.ivMovie
        private val mTitle: TextView = binding.tvName
        private val mYearOfProduction: TextView = binding.tvYearOfProduction

        /**
         * Set item data
         */
        fun setData(movie: MovieModel,onClick: (MovieModel) -> Unit) {
            val index = layoutPosition
            val context = itemView.context
            Log.i("TAG", "setData:mopve "+movie)
            Glide.with(context).load("https://image.tmdb.org/t/p/w500"+movie.poster_path)
                .into(mImage)
            mTitle.text = movie.title
            mYearOfProduction.text = movie.release_date
            itemView.setOnClickListener{
                onClick(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemHolder = holder as MovieViewHolder
        itemHolder.setData(listOfMovies.get(position),onClick)
        itemHolder.itemView.setOnClickListener {
            onClick(listOfMovies.get(position))
        }
    }


    override fun adapterItems(): ArrayList<MovieModel> {
        return arrayListOf()
    }

    override fun hasMore(): Boolean {
        return  false
    }


}