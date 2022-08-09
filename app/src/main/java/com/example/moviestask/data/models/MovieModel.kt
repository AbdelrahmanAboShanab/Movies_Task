package com.example.moviestask.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


data class MovieModel(
    var adult: Boolean,
    var video: Boolean,
    var backdrop_path: String,
    var original_language: String,
    var original_title: String,
    var overview: String,
    var release_date: String,
    var title: String,
    var popularity: Double,
    var vote_average: Double,
    var id : Int,
    var vote_count: Int,
    var genre_ids: List<Int>,
    var poster_path: String,
)