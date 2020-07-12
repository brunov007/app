package com.bruno.teste.core.data.models

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("poster_path")
    var poster_path: String,

    @SerializedName("genres")
    var genres: List<Genres>,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("original_title")
    var original_title: String,

    @SerializedName("runtime")
    var runtime: Double
)