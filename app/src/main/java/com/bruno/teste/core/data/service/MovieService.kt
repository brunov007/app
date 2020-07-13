package com.bruno.teste.core.data.service

import com.bruno.teste.core.data.models.MovieDetail
import com.bruno.teste.core.data.models.MoviesResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    companion object {
        const val LANGUAGE = "pt-BR"
        const val TOKEN = "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ZjczZDU4OGU4NGYwYTE4YWVkNGQyMTNhZjgyZjM0MSIsInN1YiI6IjVjY2I0MTdkOTI1MTQxMDQ4MTI1YjdhZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.VwqlC2O02JCS0RtI9gJhlR4f8eJDnT0AFxKnckSHJRo"
    }

    @Headers(TOKEN)
    @GET("movie/popular")
    fun fetchMovies(@Query("language") language : String = LANGUAGE): Observable<MoviesResponse>


    @Headers(TOKEN)
    @GET("movie/{id}")
    fun movieInfo(@Path("id") id: Int,
                  @Query("language") language : String = LANGUAGE): Observable<MovieDetail>
}