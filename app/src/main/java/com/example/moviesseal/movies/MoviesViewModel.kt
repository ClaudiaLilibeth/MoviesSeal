package com.example.moviesseal.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.remote.movies.models.MoviesResponse
import com.example.remote.movies.models.getGenresIds
import com.example.remote.movies.models.toEntity
import com.example.remote.movies.use_case.GetMoviesNowPlayingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesLastUseCase: com.example.remote.movies.use_case.GetMoviesLatestUseCase,
    private val moviesNowPlayingUseCase: GetMoviesNowPlayingUseCase,
    private val moviesTopRatedUseCase: com.example.remote.movies.use_case.GetMoviesTopRatedUseCase,
    private val genresUseCase: com.example.remote.movies.use_case.GetGenresUseCase,
    private val genreRepository: com.example.local.repository.GenreRepository,
    private val repository: com.example.remote.auth.data.AuthRepository,
) : ViewModel() {
    //private val _moviesNowPlaying = MutableStateFlow(ArrayList<com.example.remote.movies.models.Movie>())
    val moviesNowPlaying: LiveData<MoviesResponse> by lazy {
        moviesNowPlayingUseCase.getMoviesNowPlaying()
    }
    private val _moviesTopRated =
        MutableStateFlow(ArrayList<com.example.remote.movies.models.Movie>())
    val moviesTopRated: StateFlow<ArrayList<com.example.remote.movies.models.Movie>> get() = _moviesTopRated

    private val _moviesLast = MutableStateFlow(com.example.remote.movies.models.Movie())
    val moviesLast: StateFlow<com.example.remote.movies.models.Movie> get() = _moviesLast

    private val _genresDetail = MutableStateFlow(ArrayList<String>())
    val genresDetaill: StateFlow<ArrayList<String>> get() = _genresDetail

    init {
        CoroutineScope(Dispatchers.IO).launch {
            //_moviesNowPlaying.value = moviesNowPlayingUseCase.getMoviesNowPlaying().value.results
            _moviesTopRated.value = moviesTopRatedUseCase().results
            _moviesLast.value = moviesLastUseCase()

            genresUseCase().genresList.map {
                com.example.local.entities.GenreEntity(idGenre = it.id, name = it.name)
            }.forEach {
                genreRepository.insertGenre(it)
            }
        }
    }

    fun logOut() {
        repository.logout()
    }

    fun insertMovieAndGenre(movie: com.example.remote.movies.models.Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            genreRepository.insertMovie(
                arrayListOf(
                    movie.toEntity()
                )
            )

            //INSERT MOVIES GENRES
            val listGenresMovie = movie.getGenresIds().map { idGenre ->
                com.example.local.entities.GenresAndMoviesCross(movie.id, idGenre)
            }
            genreRepository.insertMoviesToGenre(
                listGenresMovie
            )
        }
    }

    fun getGenfromMov(idMovie: Int) {
        _genresDetail.value.clear()
        CoroutineScope(Dispatchers.IO).launch {

            val scope = coroutineScope {
                async {
                    genreRepository.getMoviesByGenre(idMovie)
                }
            }
            val result = scope.await()
            _genresDetail.value = result.genres.map { genre -> genre.name } as ArrayList<String>
        }//coroutine
    }
}