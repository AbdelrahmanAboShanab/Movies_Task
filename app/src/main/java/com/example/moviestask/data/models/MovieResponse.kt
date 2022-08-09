package com.example.moviestask.data.models

data class MovieResponse(
    var page: Int,
    var totalPages: Int,
    var totalResults: Int,
    var results: List<MovieModel>
)