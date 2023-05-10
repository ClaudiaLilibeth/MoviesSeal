package com.example.remote.movies.api

import com.example.remote.movies.models.GenreListResponse
import com.example.remote.movies.models.Movie
import com.example.remote.movies.models.MoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesDBApi {
    @GET("movie/now_playing")
    suspend fun getMoviesNowPlaying(@Query("page") page: Int): MoviesResponse

    @GET("movie/now_playing")
    fun getMoviesNowPlayingRX(@Query("page") page: Int): Single<MoviesResponse>

    @GET("movie/latest")
    suspend fun getMoviesLatest(): Movie

    @GET("movie/top_rated")
    suspend fun getMoviesTopRated(@Query("page") page: Int): MoviesResponse

    @GET("genre/movie/list")
    suspend fun getGenreList(): GenreListResponse

}