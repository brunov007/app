package com.bruno.teste.core.data.models

import com.google.gson.annotations.SerializedName

data class MoviesResponse (

    @SerializedName("page")
    var page: Int,

    @SerializedName("total_results")
    var total_results: Int,

    @SerializedName("total_pages")
    var total_pages: Int,

    @SerializedName("results")
    var list: List<Movie>
)