package com.aim2u.pokedexapp.core.application

import android.app.Application
import com.aim2u.pokedexapp.data.database.PokemonDatabase

class PokedexApplication : Application() {
    val database by lazy { PokemonDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()

        // Set the application instance
        instance = this

        PokemonDatabase.initializeDatabase(this)
    }

    companion object {
        private lateinit var instance: PokedexApplication

        // Provide a way to access the application instance
        fun getInstance(): PokedexApplication {
            return instance
        }
    }
}