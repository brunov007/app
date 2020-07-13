package com.bruno.teste.view.fragments

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bruno.teste.R
import com.bruno.teste.core.ui.BaseFragment
import com.bruno.teste.viewmodel.HomeViewModel
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_first.view.*
import kotlinx.android.synthetic.main.fragment_second.view.*
import kotlin.math.roundToInt


class SecondFragment : BaseFragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(requireActivity()).get(HomeViewModel::class.java)

        viewModel.isLoading.observe(viewLifecycleOwner,
            Observer {
                if(it)
                    requireView().progressBar_movie_Info.visibility = View.VISIBLE
                else
                    requireView().progressBar_movie_Info.visibility = View.INVISIBLE
            }
        )

        viewModel.selectedMovie.observe( viewLifecycleOwner,
            Observer { movie ->

                viewModel.showLoading()

                viewModel.getMovieDetails(movie.id).observe(viewLifecycleOwner, Observer { it ->

                    viewModel.hideLoading()

                    view.titulo_info.text = it.original_title
                    view.duracao_info.text = "Duração: ${it.runtime.roundToInt()} min"
                    view.sinopse_info.text = it.overview
                    try{
                        val IMAGEURL = "https://image.tmdb.org/t/p/w185"
                        Glide.with(this).load(IMAGEURL+it.poster_path).into(view.image_info)
                    }catch (e: Exception){
                        Log.e("BRUNO", e.toString())
                    }

                    it.genres.forEach { item ->
                        val mChip = this.layoutInflater.inflate(R.layout.item_chip_category, null, false) as Chip
                        mChip.text = item.name
                        view.chipsPrograms.addView(mChip);
                    }
                })
            }
        )
    }


}