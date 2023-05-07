package com.example.remote.movies.use_case

import com.example.remote.movies.api.MoviesDBApi
import com.example.remote.movies.models.MoviesResponse
import javax.inject.Inject


class GetMoviesNowPlayingUseCase @Inject constructor(private val api: MoviesDBApi) {
    suspend operator fun invoke(): MoviesResponse {
        return api.getMoviesNowPlaying(1)
    }
}