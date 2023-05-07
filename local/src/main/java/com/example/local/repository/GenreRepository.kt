package com.example.local.repository

import com.example.local.entities.GenreEntity
import com.example.local.entities.GenresAndMoviesCross
import com.example.local.entities.MovieEntity
import com.example.local.entities.MoviesWithGenre
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