package com.example.moviesseal.movies.api

import com.example.moviesseal.movies.models.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesDBApi {
    @GET("movie/now_playing")
    fun getMoviesNowPlaying(@Query("page")page:Int): MoviesResponse

    @GET("movie/latest")
    fun getMoviesLatest():MoviesResponse

    @GET("movie/top_rated")
    fun getMoviesTopRated(@Query("page")page:Int):MoviesResponse


}