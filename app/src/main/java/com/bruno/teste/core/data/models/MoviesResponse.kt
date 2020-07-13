package com.bruno.teste.core.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel

data class MoviesResponse (

    @IgnoredOnParcel
    var errorResult: String,

    @SerializedName("page")
    var page: Int = 1,

    @SerializedName("total_results")
    var total_results: Int = 1,

    @SerializedName("total_pages")
    var total_pages: Int = 1,

    @SerializedName("results")
    var list: List<Movie> = emptyList()
)