package com.example.local.entities

import androidx.room.Entity

@Entity(primaryKeys = ["id", "idGenre"])
data class GenresAndMoviesCross(
    val id: Int,
    val idGenre: Int,
)