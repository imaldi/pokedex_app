package com.aim2u.pokedexapp.core.application

import android.app.Application
import com.aim2u.pokedexapp.data.database.PokemonDatabase

class PokedexApplication : Application() {
    val database by lazy { PokemonDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()

        PokemonDatabase.initializeDatabase(this)
    }
}