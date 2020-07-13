package com.bruno.teste.core.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel

data class MovieDetail(

    @IgnoredOnParcel
    var errorResult: String,

    @SerializedName("poster_path")
    var poster_path: String = "",

    @SerializedName("genres")
    var genres: List<Genres> = emptyList(),

    @SerializedName("overview")
    var overview: String = "",

    @SerializedName("original_title")
    var original_title: String = "",

    @SerializedName("runtime")
    var runtime: Double = 1.toDouble()
)