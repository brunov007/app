package com.bruno.teste.core.data.repository

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bruno.teste.core.data.models.Movie
import com.bruno.teste.core.data.models.MovieDetail
import com.bruno.teste.core.data.models.MoviesResponse
import com.bruno.teste.core.data.service.MovieFactory
import com.bruno.teste.core.utils.ConnectivityUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.net.UnknownHostException


class HomeRepositoryImpl : HomeRepository{

    @SuppressLint("CheckResult")
    override fun getMoviesList(responseError: (e: String) -> Unit): LiveData<List<Movie>?> {
        val data = MutableLiveData<List<Movie>>()

        val service = MovieFactory().create()

        service.fetchMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ movieResponse -> data.postValue(movieResponse.list)},
                {error ->
                    if(error is UnknownHostException){
                        responseError("Ocorreu um erro ao requisitar os dados, verifique sua conexao com a internet")
                    }else if(error is HttpException){
                        responseError(error.message())
                    }
            })

        return data
    }

    @SuppressLint("CheckResult")
    override fun getMovieDetail(id: Int, responseError: (e: String) -> Unit): LiveData<MovieDetail> {
        val data = MutableLiveData<MovieDetail>()

        val service = MovieFactory().create()

        service.movieInfo(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ details -> data.postValue(details)},
                {error ->
                    if(error is UnknownHostException){
                         responseError("Ocorreu um erro ao requisitar os dados, verifique sua conexao com a internet")
                    }else if(error is HttpException){
                        responseError(error.message())
                    }
                })

        return data
    }
}