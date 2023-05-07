package com.example.moviesseal.movies

import androidx.lifecycle.ViewModel
import com.example.local.entities.GenreEntity
import com.example.local.entities.GenresAndMoviesCross
import com.example.local.repository.GenreRepository
import com.example.moviesseal.remote.auth.data.AuthRepository
import com.example.moviesseal.remote.movies.models.Movie
import com.example.moviesseal.remote.movies.models.getGenresIds
import com.example.moviesseal.remote.movies.models.toEntity
import com.example.moviesseal.remote.movies.use_case.GetGenresUseCase
import com.example.moviesseal.remote.movies.use_case.GetMoviesLatestUseCase
import com.example.moviesseal.remote.movies.use_case.GetMoviesNowPlayingUseCase
import com.example.moviesseal.remote.movies.use_case.GetMoviesTopRatedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesLastUseCase: GetMoviesLatestUseCase,
    private val moviesNowPlayingUseCase: GetMoviesNowPlayingUseCase,
    private val moviesTopRatedUseCase: GetMoviesTopRatedUseCase,
    private val genresUseCase: GetGenresUseCase,
    private val genreRepository: com.example.local.repository.GenreRepository,
    private val repository: AuthRepository,
) : ViewModel() {
    private val _moviesNowPlaying = MutableStateFlow(ArrayList<Movie>())
    val moviesNowPlaying: StateFlow<ArrayList<Movie>> get() = _moviesNowPlaying

    private val _moviesTopRated = MutableStateFlow(ArrayList<Movie>())
    val moviesTopRated: StateFlow<ArrayList<Movie>> get() = _moviesTopRated

    private val _moviesLast = MutableStateFlow(Movie())
    val moviesLast: StateFlow<Movie> get() = _moviesLast

    private val _genresDetail = MutableStateFlow(ArrayList<String>())
    val genresDetaill: StateFlow<ArrayList<String>> get() = _genresDetail

    init {
        CoroutineScope(Dispatchers.IO).launch {
            _moviesNowPlaying.value = moviesNowPlayingUseCase().results
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

    fun insertMovieAndGenre(movie: Movie) {
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