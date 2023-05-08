package com.example.remote.movies.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.remote.movies.api.MoviesDBApi
import com.example.remote.movies.models.MoviesResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MoviesDataSource @Inject constructor(
    private val api: MoviesDBApi,
    private val compositeDisposable: CompositeDisposable
) {
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState> get() = _networkState

    private val _moviesResponse = MutableLiveData(MoviesResponse())
    val moviesResponse: LiveData<MoviesResponse> get() = _moviesResponse

    fun getMoviesNowPlaying() {
        _networkState.postValue(NetworkState.LOADING)

        try {
            compositeDisposable.add(
                api.getMoviesNowPlayingRX(1)
                    .subscribeOn(Schedulers.io()).subscribe(
                        {
                            _moviesResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        }, {
                            _networkState.postValue(NetworkState.ERROR)
                            it.message?.let { message -> Log.e("HTTP_ERROR", message) }
                        }
                    )
            )
        } catch (e: Exception) {
            e.message?.let { message -> Log.e("HTTP_ERROR_CATCH", message)}
        }
    }
}