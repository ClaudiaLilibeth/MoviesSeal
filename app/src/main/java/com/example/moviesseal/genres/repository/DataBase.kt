package com.example.moviesseal.genres.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesseal.genres.entities.GenreEntity
import com.example.moviesseal.genres.entities.GenresAndMoviesCross
import com.example.moviesseal.genres.entities.MovieEntity

@Database(
    entities = [GenreEntity::class, MovieEntity::class, GenresAndMoviesCross::class],
    version = 1,
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {
    abstract fun genresDao(): GenresDao
}