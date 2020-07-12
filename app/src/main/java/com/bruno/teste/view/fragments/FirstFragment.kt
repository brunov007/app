package com.bruno.teste.view.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bruno.teste.R
import com.bruno.teste.core.data.models.Movie
import com.bruno.teste.core.ui.BaseFragment
import com.bruno.teste.view.adapters.RecyclerViewAdapter
import com.bruno.teste.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_first.view.*

class FirstFragment : BaseFragment() {

    private lateinit var viewModel: HomeViewModel
    private var adapter: RecyclerViewAdapter? = null

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

        //showLoading()

        viewModel.getMoviesListService().observe(viewLifecycleOwner,
            Observer { list ->
                list?.let {
                    if (adapter == null) configureRecyclerView(requireView(), list) else adapter!!.setMovieList(list)
                }
            }
        )
    }

    private fun configureRecyclerView(view: View, list:List<Movie> = emptyList()){
        view.recyclerView.setHasFixedSize(true)
        view.recyclerView.layoutManager = GridLayoutManager(context, 3)

        view.recyclerView.adapter = RecyclerViewAdapter(list){ movie ->
            viewModel.selectedMovie.value=movie
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}