package com.bruno.teste.core.data.repository

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bruno.teste.core.data.models.Movie
import com.bruno.teste.core.data.models.MovieDetail
import com.bruno.teste.core.data.service.MovieFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class HomeRepositoryImpl : HomeRepository{

    @SuppressLint("CheckResult")
    override fun getMoviesList(): LiveData<List<Movie>?> {
        val data = MutableLiveData<List<Movie>>()

        val service = MovieFactory().create()

        service.fetchMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ movieResponse -> data.postValue(movieResponse.list)},
                {error ->
                    //TODO
            })

        return data
    }

    @SuppressLint("CheckResult")
    override fun getMovieDetail(id: Int): LiveData<MovieDetail> {
        val data = MutableLiveData<MovieDetail>()

        val service = MovieFactory().create()

        service.movieInfo(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ details -> data.postValue(details)},
                {error ->
                    //TODO
                })

        return data
    }
}