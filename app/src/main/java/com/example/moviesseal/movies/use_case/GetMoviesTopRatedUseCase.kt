package com.example.moviesseal.movies.use_case

import com.example.moviesseal.movies.api.MoviesDBApi
import com.example.moviesseal.movies.models.MoviesResponse
import javax.inject.Inject

class GetMoviesTopRatedUseCase @Inject constructor(private val api: MoviesDBApi) { //para hacerlo directamente al crearlo
    suspend operator fun invoke(): MoviesResponse {
        return api.getMoviesTopRated(1)
    }
}