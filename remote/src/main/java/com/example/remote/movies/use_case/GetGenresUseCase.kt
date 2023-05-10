package com.example.remote.movies.use_case

import com.example.remote.movies.api.MoviesDBApi
import com.example.remote.movies.models.GenreListResponse
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(private val api: MoviesDBApi) {
    suspend operator fun invoke(): GenreListResponse {
        return api.getGenreList()
    }
}