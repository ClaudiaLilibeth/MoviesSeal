package com.example.local.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.local.entities.GenreEntity
import com.example.local.entities.GenresAndMoviesCross
import com.example.local.entities.MovieEntity

@Database(
    entities = [GenreEntity::class, MovieEntity::class, GenresAndMoviesCross::class],
    version = 1,
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {
    abstract fun genresDao(): GenresDao
}