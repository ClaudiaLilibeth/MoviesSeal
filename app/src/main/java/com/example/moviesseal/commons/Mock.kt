package com.example.moviesseal.commons

import com.example.local.entities.GenreEntity
import com.example.local.entities.GenresAndMoviesCross
import com.example.local.entities.MovieEntity

object Mock{
    fun createGenreEntitySuspenso(): GenreEntity{
     return GenreEntity(idGenre = 1, name = "Suspenso")
    }

    fun createGenreEntityBelico(): GenreEntity{
        return GenreEntity(idGenre = 2, name = "Belico")
    }

    fun createGenreEntityHistorico(): GenreEntity{
        return GenreEntity(idGenre = 3, name = "Historico")
    }

    fun createMoviesList(): List<MovieEntity>{
        return  listOf<MovieEntity>(
            MovieEntity(1, "El ataque de las focas VII"),
            MovieEntity(2, "Joaquín la capybara"),
            MovieEntity(3, "El pambazo asesino"))
    }

    fun createMovieGenreCross(): List<GenresAndMoviesCross>{
        return listOf(
            GenresAndMoviesCross(1,3),
            GenresAndMoviesCross(2,2),
            GenresAndMoviesCross(3,1)
        )
    }

}