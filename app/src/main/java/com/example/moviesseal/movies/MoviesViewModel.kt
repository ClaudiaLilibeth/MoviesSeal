package com.example.moviesseal.movies

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.moviesseal.movies.models.Movie
import com.example.moviesseal.movies.use_case.GetMoviesLatestUseCase
import com.example.moviesseal.movies.use_case.GetMoviesNowPlayingUseCase
import com.example.moviesseal.movies.use_case.GetMoviesTopRatedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesLastUseCase: GetMoviesLatestUseCase,
    private val moviesNowPlayingUseCase: GetMoviesNowPlayingUseCase,
    private val moviesTopRatedUseCase: GetMoviesTopRatedUseCase,
) : ViewModel() {
    private val _moviesNowPlaying = MutableStateFlow(ArrayList<Movie>())
    val moviesNowPlaying: StateFlow<ArrayList<Movie>> get() = _moviesNowPlaying

    private val _selectedMovie = mutableStateOf(Movie())
    val selectedMovie: MutableState<Movie> get() = _selectedMovie


    private val _moviesTopRated = MutableStateFlow(ArrayList<Movie>())
    val moviesTopRated: StateFlow<ArrayList<Movie>> get() = _moviesTopRated

    private val _moviesLast = MutableStateFlow(Movie())
    val moviesLast: StateFlow<Movie> get() = _moviesLast

    init {
        CoroutineScope(Dispatchers.IO).launch {
            _moviesNowPlaying.value = moviesNowPlayingUseCase().results
            _moviesTopRated.value = moviesTopRatedUseCase().results
            _moviesLast.value = moviesLastUseCase()
        }
    }

    fun setSelectedMovie(movie: Movie) {
        _selectedMovie.value = movie
    }
}