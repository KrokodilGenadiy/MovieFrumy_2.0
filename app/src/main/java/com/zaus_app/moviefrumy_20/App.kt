package com.zaus_app.moviefrumy_20

import android.app.Application
import com.zaus_app.moviefrumy_20.data.MainRepository
import com.zaus_app.moviefrumy_20.domain.Interactor

class App : Application() {
    lateinit var repo: MainRepository
    lateinit var interactor: Interactor

    override fun onCreate() {
        super.onCreate()
        instance = this
        repo = MainRepository()
        interactor = Interactor(repo)
    }

    companion object {
        lateinit var instance: App
            private set
    }
}