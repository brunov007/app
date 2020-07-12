package com.bruno.teste.core.data.repository

import androidx.lifecycle.LiveData
import com.bruno.teste.core.data.models.Movie
import com.bruno.teste.core.data.models.MovieDetail

interface HomeRepository {

    fun getMoviesList() : LiveData<List<Movie>?>

    fun getMovieDetail(id: Int) : LiveData<MovieDetail>
}