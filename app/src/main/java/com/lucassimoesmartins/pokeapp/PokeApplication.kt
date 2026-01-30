package com.lucassimoesmartins.pokeapp

import android.app.Application
import com.lucassimoesmartins.pokeapp.di.AppContainer

class PokeApplication : Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}