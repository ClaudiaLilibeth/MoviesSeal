package com.example.remote.movies.use_case

import androidx.lifecycle.LiveData
import com.example.remote.movies.models.MoviesResponse
import com.example.remote.movies.repository.MoviesDataSource
import com.example.remote.movies.repository.NetworkState
import javax.inject.Inject


class GetMoviesNowPlayingUseCase @Inject constructor(
    private val moviesDataSource: MoviesDataSource,
) {
    /*suspend operator fun invoke(): MoviesResponse {
        return api.getMoviesNowPlaying(1)
    }*/

    fun getMoviesNowPlaying(): LiveData<MoviesResponse> {
        moviesDataSource.getMoviesNowPlaying()

        return moviesDataSource.moviesResponse
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return moviesDataSource.networkState
    }
}