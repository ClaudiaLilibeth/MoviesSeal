package com.example.moviesseal.movies.api

import com.example.moviesseal.movies.models.Movie
import com.example.moviesseal.movies.models.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesDBApi {
    @GET("movie/now_playing")
    suspend fun getMoviesNowPlaying(@Query("page")page:Int): MoviesResponse

    @GET("movie/latest")
    suspend fun getMoviesLatest():Movie

    @GET("movie/top_rated")
    suspend fun getMoviesTopRated(@Query("page")page:Int):MoviesResponse


}