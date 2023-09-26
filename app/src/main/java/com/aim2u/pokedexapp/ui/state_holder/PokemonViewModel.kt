package com.aim2u.pokedexapp.ui.state_holder

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.aim2u.pokedexapp.data.model.Result
import com.aim2u.pokedexapp.data.network.ApiService

class PokemonViewModel : ViewModel() {
    private val apiService = ApiService.create()

    val pokemonList: LiveData<List<Result>> = liveData {
        val response = apiService.getPokemonList()
        Log.d("pokemon response","$response")
        emit(response.results)
    }
}