package com.bruno.teste.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bruno.teste.core.data.models.Movie
import com.bruno.teste.core.data.models.MovieDetail
import com.bruno.teste.core.data.repository.HomeRepository
import com.bruno.teste.core.data.repository.HomeRepositoryImpl
import com.bruno.teste.core.utils.ConnectivityUtils
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity
import com.github.pwittchen.reactivenetwork.library.rx2.ConnectivityPredicate
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class HomeViewModel(private val repository: HomeRepository = HomeRepositoryImpl()) : ViewModel(){

    val listMovies = MutableLiveData<List<Movie>?>()

    val selectedMovie = MutableLiveData<Movie>()

    val isLoading = MutableLiveData<Boolean>()

    val errorResponse = MutableLiveData<String>()

    val connectivityAvailable = MutableLiveData<Boolean>()

    @SuppressLint("CheckResult")
    fun initConnectivity(context: Context){
        ReactiveNetwork
            .observeNetworkConnectivity(context)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({connectivity ->
                connectivityAvailable.value = connectivity.available()
            },{error -> print(error)})
    }

    fun getMovieDetails(id:Int) : LiveData<MovieDetail>{
        return repository.getMovieDetail(id){
            errorResponse.value = it
        }
    }

    fun getMoviesListService() : LiveData<List<Movie>?> {
        return repository.getMoviesList{
            errorResponse.value = it
        }
    }

    fun showLoading(){
        isLoading.value = true
    }

    fun hideLoading(){
        isLoading.value = false
    }
}