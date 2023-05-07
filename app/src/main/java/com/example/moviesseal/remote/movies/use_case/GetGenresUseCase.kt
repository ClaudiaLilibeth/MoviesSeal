package com.example.moviesseal.remote.movies.use_case

import com.example.moviesseal.remote.movies.api.MoviesDBApi
import com.example.moviesseal.remote.movies.models.GenreListResponse
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(private val api: MoviesDBApi) {
    suspend operator fun invoke(): GenreListResponse {
        return api.getGenreList()
    }
}