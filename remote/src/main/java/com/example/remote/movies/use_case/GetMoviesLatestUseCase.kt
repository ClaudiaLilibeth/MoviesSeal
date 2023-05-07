package com.example.remote.movies.use_case

import com.example.remote.movies.api.MoviesDBApi
import com.example.remote.movies.models.Movie
import javax.inject.Inject

class GetMoviesLatestUseCase @Inject constructor(private val api: MoviesDBApi) {
    suspend operator fun invoke(): Movie {
        return api.getMoviesLatest()
    }
}