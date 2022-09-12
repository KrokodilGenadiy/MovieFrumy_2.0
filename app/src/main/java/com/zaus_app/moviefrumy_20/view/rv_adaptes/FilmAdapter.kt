package com.zaus_app.moviefrumy_20.view.rv_adaptes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zaus_app.moviefrumy_20.view.rv_viewholders.FilmViewHolder
import com.zaus_app.moviefrumy_20.domain.Film
import com.zaus_app.moviefrumy_20.databinding.FilmItemBinding

class FilmAdapter(private val clickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items = mutableListOf<Film>()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = FilmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding) {
            clickListener.click(it)
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FilmViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    fun getItems(): List<Film> {
        return items
    }

    fun setItems(list: List<Film>) {
        this.items = list as MutableList<Film>
    }

    interface OnItemClickListener {
        fun click(position: Int)
    }
}