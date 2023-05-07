package com.example.moviesseal.genres.entities

import androidx.room.Entity

@Entity(primaryKeys = ["id", "idGenre"])
data class GenresAndMoviesCross(
    val id: Int,
    val idGenre: Int,
)