package com.example.local.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MoviesWithGenre(
    @Embedded val movie: MovieEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "idGenre",
        associateBy = Junction(GenresAndMoviesCross::class)
    )
    val genres: List<GenreEntity>,
)
