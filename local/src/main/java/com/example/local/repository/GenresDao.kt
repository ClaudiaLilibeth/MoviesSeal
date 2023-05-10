package com.example.local.repository

import androidx.room.*
import com.example.local.entities.GenreEntity
import com.example.local.entities.GenresAndMoviesCross
import com.example.local.entities.MovieEntity
import com.example.local.entities.MoviesWithGenre

@Dao
interface GenresDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genre: GenreEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenreToMovie(moviesWithGenre: List<GenresAndMoviesCross>)

    @Transaction
    @Query("SELECT *FROM MovieEntity " + " WHERE id = :id")
    suspend fun getMoviesByGenre(id: Int): MoviesWithGenre
}
