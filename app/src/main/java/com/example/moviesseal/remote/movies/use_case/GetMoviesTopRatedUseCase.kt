package com.example.moviesseal.remote.movies.use_case

import com.example.moviesseal.remote.movies.api.MoviesDBApi
import com.example.moviesseal.remote.movies.models.MoviesResponse
import javax.inject.Inject

class GetMoviesTopRatedUseCase @Inject constructor(private val api: MoviesDBApi) { //para hacerlo directamente al crearlo
    suspend operator fun invoke(): MoviesResponse {
        return api.getMoviesTopRated(1)
    }
}