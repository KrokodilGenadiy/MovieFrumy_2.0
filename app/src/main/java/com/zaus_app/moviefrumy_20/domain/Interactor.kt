package com.zaus_app.moviefrumy_20.domain

import com.zaus_app.moviefrumy_20.data.MainRepository

class Interactor(val repo: MainRepository) {
    fun getFilmsDB(): List<Film> = repo.filmsDataBase
}