package com.example.moviesseal.remote.movies.models

data class MoviesResponse(
    var page: Int,
    var results: ArrayList<Movie>,
    var total_results: Int,
    var total_pages: Int,
)