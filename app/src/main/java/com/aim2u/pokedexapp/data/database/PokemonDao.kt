package com.aim2u.pokedexapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aim2u.pokedexapp.data.model.PokemonEntity

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemonList: List<PokemonEntity>)

    @Query("SELECT * FROM pokemon_table")
    fun getAllPokemon(): LiveData<List<PokemonEntity>>
}