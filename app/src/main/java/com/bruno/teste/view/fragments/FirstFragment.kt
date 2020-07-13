package com.bruno.teste.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bruno.teste.R
import com.bruno.teste.core.data.models.Movie
import com.bruno.teste.core.data.models.db.TB_Movie
import com.bruno.teste.core.ui.BaseFragment
import com.bruno.teste.core.ui.GridSpacingItemDecoration
import com.bruno.teste.core.ui.VerticalItemDecoration
import com.bruno.teste.core.utils.EspressoIdlingResource
import com.bruno.teste.view.adapters.RecyclerViewAdapter
import com.bruno.teste.viewmodel.HomeViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orm.SugarRecord
import kotlinx.android.synthetic.main.fragment_first.view.*
import kotlin.collections.List


class FirstFragment : BaseFragment() {

    companion object{
        const val SPAN_COUNT = 2
    }

    private lateinit var viewModel: HomeViewModel
    private var adapter: RecyclerViewAdapter? = null
    private lateinit var gridLayoutManager: GridLayoutManager

    private val gridDecoration: RecyclerView.ItemDecoration by lazy {
        GridSpacingItemDecoration(
            SPAN_COUNT, resources.getDimension(R.dimen.margin_grid).toInt()
        )
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        viewModel = ViewModelProviders.of(requireActivity()).get(HomeViewModel::class.java)

        configureRecyclerView(view)

        return view
    }

    override fun onStart() {
        super.onStart()

        /* USE THIS ONLY TO MOCK DATA
        if(ApplicationProperties.get(ApplicationPropertiesEnum.MOCK_ENABLED).toBoolean()){
            val mockResult = AndroidUtils.readRawFileString(context,R.raw.movies, "UTF-8")
            val turnsType = object : TypeToken<MoviesResponse>() {}.type
            val list = Gson().fromJson<List<Movie>>(mockResult, turnsType)

            if (adapter == null) configureRecyclerView(requireView(), list) else adapter!!.setMovieList(list)

            return
        }
         */

        EspressoIdlingResource.increment()

        val result = SugarRecord.findAll(TB_Movie::class.java)

        //FIXME esta retornando a primeira posicao da tabela
        if(result.hasNext()){
            val turnsType = object : TypeToken<List<Movie>>() {}.type
            val movieList = Gson().fromJson<List<Movie>>(result.next().list, turnsType) //Mudar tipo lista generica(LinkedTreeMap) em Lista<Movies>

            if (adapter == null) configureRecyclerView(requireView(), movieList) else adapter!!.setMovieList(movieList)
        }

        if(!viewModel.getMoviesListService().hasObservers()){
            viewModel.getMoviesListService().observe(viewLifecycleOwner,
                Observer { list ->
                    list?.let {

                        val modelDAO = TB_Movie()
                        modelDAO.list = Gson().toJson(it)
                        modelDAO.save()

                        if (adapter == null) configureRecyclerView(requireView(), list) else adapter!!.setMovieList(list)

                        EspressoIdlingResource.decrement()
                    }
                }
            )
        }
    }


    private fun configureRecyclerView(view: View, list: List<Movie> = emptyList()){
        view.recyclerView.setHasFixedSize(true)
        gridLayoutManager = GridLayoutManager(context, SPAN_COUNT)
        view.recyclerView.addItemDecoration(gridDecoration)
        view.recyclerView.layoutManager = gridLayoutManager

        adapter = RecyclerViewAdapter(list){ movie ->
            viewModel.selectedMovie.value=movie
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        view.recyclerView.adapter = adapter
    }
}