package com.zaus_app.moviefrumy_20

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zaus_app.moviefrumy_20.databinding.FilmItemBinding


class FilmViewHolder(binding: FilmItemBinding, clickAtPosition: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    private val title = binding.title
    private val poster = binding.poster
    private val description = binding.description
    private val ratingDonut = binding.ratingDonut

    init {
        binding.root.setOnClickListener {
            clickAtPosition(adapterPosition)
        }
    }

    fun bind(film: Film) {
        title.text = film.title
        Glide.with(itemView)
            .load(film.poster)
            .centerCrop()
            .into(poster)
        description.text = film.description
        ratingDonut.setProgress((film.rating * 10).toInt())
        ratingDonut.animateProgress()
    }
}