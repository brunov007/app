package com.bruno.teste.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bruno.teste.core.data.models.Movie
import com.bruno.teste.core.data.models.MovieDetail
import com.bruno.teste.core.data.models.MoviesResponse
import com.bruno.teste.core.data.repository.HomeRepository
import com.bruno.teste.core.data.repository.HomeRepositoryImpl

class HomeViewModel(private val repository: HomeRepository = HomeRepositoryImpl()) : ViewModel(){

    val listMovies = MutableLiveData<List<Movie>?>()

    val selectedMovie = MutableLiveData<Movie>()

    val isLoading = MutableLiveData<Boolean>()

    fun getMoviesListService() : LiveData<List<Movie>?> {
        return repository.getMoviesList()
    }

    fun getMovieDetails(id:Int) : LiveData<MovieDetail>{
        return repository.getMovieDetail(id)
    }

    fun showLoading(){
        isLoading.value = true
    }

    fun hideLoading(){
        isLoading.value = false
    }
}