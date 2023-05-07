package com.example.moviesseal.movies.use_case

import com.example.moviesseal.movies.api.MoviesDBApi
import com.example.moviesseal.movies.models.GenreListResponse
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(private val api: MoviesDBApi) {
    suspend operator fun invoke(): GenreListResponse {
        return api.getGenreList()
    }
}