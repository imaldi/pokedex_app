package com.aim2u.pokedexapp.data.repository

import androidx.lifecycle.LiveData
import com.aim2u.pokedexapp.data.database.PokemonDao
import com.aim2u.pokedexapp.data.model.PokemonEntity

class PokemonRepository(private val pokemonDao: PokemonDao) {
    val allPokemon: LiveData<List<PokemonEntity>> = pokemonDao.getAllPokemon()

    suspend fun insertPokemon(pokemonList: List<PokemonEntity>) {
        pokemonDao.insertAll(pokemonList)
    }
}