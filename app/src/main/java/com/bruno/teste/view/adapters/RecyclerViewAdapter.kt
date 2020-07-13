package com.bruno.teste.view.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bruno.teste.R
import com.bruno.teste.core.data.models.Movie
import com.bruno.teste.core.utils.EspressoIdlingResource
import kotlinx.android.synthetic.main.cell.view.*
import java.lang.Exception
import com.bumptech.glide.Glide

class RecyclerViewAdapter(private var list: List<Movie> = emptyList(), private val listener: (Movie) -> Unit) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context;

        val view = LayoutInflater.from(context).inflate(R.layout.cell, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        //holder.view.setBackgroundColor(context.resources.getColor(R.color.colorAccent))

        holder.title.text = item.original_title

        if (item.backdrop_path.isNotBlank()){
            try{
                val IMAGEURL = "https://image.tmdb.org/t/p/w185"

                Glide.with(context).load(IMAGEURL+item.poster_path).into(holder.image)
            }catch (e: Exception){
                Log.e("BRUNO", e.toString())
            }
        }

        holder.view.setOnClickListener{
            listener(item)
        }
    }

    fun setMovieList(movieList: List<Movie>){
        list = movieList
        notifyDataSetChanged()
        EspressoIdlingResource.decrement()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title = itemView.titulo
        val image = itemView.image
        val view = itemView
    }
}