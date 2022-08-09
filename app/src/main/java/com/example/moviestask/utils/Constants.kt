package com.example.moviestask.utils

object Constants {

    const val API = "https://api.themoviedb.org"
    const val MOVIES_ENDPOINT = "/3/movie/popular?api_key=442bfb98d6070572786202373c892719"
    const val GENRES_ENDPOINT = "/3/genre/movie/list?api_key=442bfb98d6070572786202373c892719"
}

enum class Status { LOADING, LOADING_MORE, SUCCESS, ERROR }