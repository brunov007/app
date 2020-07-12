package com.bruno.teste.core

import android.app.Application
import android.content.Context
import com.bruno.teste.core.data.service.MovieService
import com.bruno.teste.core.data.service.MovieFactory
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class CustomApplication : Application(){

    init {
        instance = this
        movieService = MovieFactory().create()
        scheduler = Schedulers.io()
    }

    companion object {
        private var instance: CustomApplication? = null
        private var movieService: MovieService? = null
        private var scheduler: Scheduler? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }

        fun getMovieService(): MovieService {
            return movieService as MovieService
        }

        fun getScheduler() : Scheduler{
            return scheduler as Scheduler
        }
    }


    override fun onCreate() {
        super.onCreate()

        val context: Context = CustomApplication.applicationContext()
    }
}