package com.zaus_app.moviefrumy_20.data

import com.zaus_app.moviefrumy_20.domain.Film

object FavoritesDatabase {
    val favoritesList: MutableList<Film> = emptyList<Film>().toMutableList()
}