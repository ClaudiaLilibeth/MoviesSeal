package com.example.moviesseal.movies.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenreListResponse(
    @SerializedName("genres")
    var genresList: ArrayList<GenreResponse> = arrayListOf<GenreResponse>(),
) : Serializable

data class GenreResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
) : Serializable