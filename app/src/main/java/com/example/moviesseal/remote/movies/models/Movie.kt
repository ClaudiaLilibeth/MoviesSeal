package com.example.moviesseal.remote.movies.models

import com.example.local.entities.MovieEntity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(
    @SerializedName("adult") var adult: Boolean = false,
    @SerializedName("backdrop_path") var backdrop_path: String = "",
    @SerializedName("genre_ids") var genre_ids: List<Int> = emptyList(),
    @SerializedName("id") var id: Int = 0,
    @SerializedName("overview") var overview: String = "",
    @SerializedName("popularity") var popularity: Double = 0.0,
    @SerializedName("poster_path") var poster_path: String = "",
    @SerializedName("release_date") var release_date: String = "",
    @SerializedName("title") var title: String = "",
    @SerializedName("video") var video: Boolean = false,
    @SerializedName("vote_average") var vote_average: Double = 0.0,
    @SerializedName("vote_count") var vote_count: Int = 0,
    @SerializedName("portada_id") var portada_id: Int = 0,
    @SerializedName("poster_id") var poster_id: Int = 0,
) : Serializable

fun Movie.toEntity(): com.example.local.entities.MovieEntity {
    return com.example.local.entities.MovieEntity(this.id, this.title)
}

fun Movie.getGenresIds() = this.genre_ids