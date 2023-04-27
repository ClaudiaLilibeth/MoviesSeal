package com.example.moviesseal.movies.use_case

import com.example.moviesseal.movies.api.MoviesDBApi
import com.example.moviesseal.movies.models.Movie
import javax.inject.Inject

class GetMoviesLatestUseCase @Inject constructor(private val api: MoviesDBApi) {
    suspend operator fun invoke(): Movie {
        return api.getMoviesLatest()
    }
}