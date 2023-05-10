package com.example.remote.movies.use_case

import com.example.remote.movies.api.MoviesDBApi
import com.example.remote.movies.models.MoviesResponse
import javax.inject.Inject

class GetMoviesTopRatedUseCase @Inject constructor(private val api: MoviesDBApi) { //para hacerlo directamente al crearlo
    suspend operator fun invoke(): MoviesResponse {
        return api.getMoviesTopRated(1)
    }
}