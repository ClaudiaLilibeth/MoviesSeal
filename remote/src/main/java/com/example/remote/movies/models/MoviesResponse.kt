package com.example.remote.movies.models

data class MoviesResponse(
    var page: Int = 0,
    var results: ArrayList<Movie> = arrayListOf(),
    var total_results: Int = 0,
    var total_pages: Int = 0,
)