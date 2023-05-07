package com.example.moviesseal.remote.movies.use_case

import com.example.moviesseal.remote.movies.api.MoviesDBApi
import com.example.moviesseal.remote.movies.models.Movie
import javax.inject.Inject

class GetMoviesLatestUseCase @Inject constructor(private val api: MoviesDBApi) {
    suspend operator fun invoke(): Movie {
        return api.getMoviesLatest()
    }
}