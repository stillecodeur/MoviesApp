package com.zenjob.android.browsr.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Movie(
    @SerializedName("id")
    val id: Long,
    @SerializedName("imdbId")
    val imdbId: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("title")
    val title: String,
    @SerializedName("status")
    val status: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("release_date")
    val releaseDate: Date?,
    @SerializedName("vote_average")
    val voteAverage: Float?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("genres")
    val genres: List<Genre>?
) : Serializable

data class Genre(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String
)
