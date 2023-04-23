package com.example.moviesseal.movies.models

data class MoviesResponse (
    val page: Int,
    val results: ArrayList<Movie>,
    val total_results:Int,
    val total_pages: Int,
    val dates: ArrayList<String>
    )