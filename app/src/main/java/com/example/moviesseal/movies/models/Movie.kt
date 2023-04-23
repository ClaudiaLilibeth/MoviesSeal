package com.example.moviesseal.movies.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName ("poster_path") var poster:String = "",
    @SerializedName ("adult") var adult: Boolean = false,
    @SerializedName ("overview") var overview: String = "",
    @SerializedName ("release_date") var releaseDate: String = "",
    @SerializedName ("genre_ids") var genreIs : List<Int> = emptyList(),
    @SerializedName ("id") var id: Int = 0,
    @SerializedName ("original_title") var originalTitle: String = "",
    @SerializedName ("original_language") var language: String = "",
    @SerializedName ("title") var title: String = "",
    @SerializedName ("backdrop_path") var backdrop: String? = "",
    @SerializedName ("popularity") var popularity: Number = 0,
    @SerializedName ("vote_count") var voteCount: Int = 0,
    @SerializedName ("video") var video: Boolean = false,
    @SerializedName ("vote_average") var voteAverage: Number = 0








    )
