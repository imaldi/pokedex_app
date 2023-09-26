package com.aim2u.pokedexapp.ui.state_holder

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.aim2u.pokedexapp.data.model.PokemonModel
import com.aim2u.pokedexapp.data.model.Result
import com.aim2u.pokedexapp.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonViewModel : ViewModel() {
    private val apiService = ApiService.create()
    // Create a MutableState to hold the Pokemon detail
    private val _pokemonDetail = MutableStateFlow<PokemonModel?>(null)
    val pokemonDetail: StateFlow<PokemonModel?> = _pokemonDetail.asStateFlow()


    val pokemonList: LiveData<List<Result>> = liveData {
        val response = apiService.getPokemonList()
        Log.d("pokemon response","$response")
        emit(response.results)
    }

    fun fetchPokemonByName(name: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = apiService.getPokemonByName(name)
                    _pokemonDetail.value = response
                } catch (e: Exception) {
                    e.printStackTrace()
                    // Handle the error
                }
            }
        }
    }
}