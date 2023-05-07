package com.example.moviesseal.genres.repository

import com.example.moviesseal.genres.entities.GenreEntity
import com.example.moviesseal.genres.entities.GenresAndMoviesCross
import com.example.moviesseal.genres.entities.MovieEntity
import com.example.moviesseal.genres.entities.MoviesWithGenre
import javax.inject.Inject

class GenreRepository @Inject constructor(
    private val dao: GenresDao,
) {
    suspend fun insertGenre(genre: GenreEntity) {
        dao.insertGenre(genre)
    }

    suspend fun insertMovie(movies: ArrayList<MovieEntity>) {
        dao.insertMovie(movies)
    }

    suspend fun insertMoviesToGenre(genresAndMovieCrosses: List<GenresAndMoviesCross>) {
        dao.insertGenreToMovie(genresAndMovieCrosses)
    }

    suspend fun getMoviesByGenre(movieId: Int): MoviesWithGenre {
        return dao.getMoviesByGenre(movieId)
    }
}