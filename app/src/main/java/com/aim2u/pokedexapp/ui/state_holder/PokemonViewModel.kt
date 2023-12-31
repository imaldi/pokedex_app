package com.aim2u.pokedexapp.ui.state_holder

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.aim2u.pokedexapp.core.application.PokedexApplication
import com.aim2u.pokedexapp.data.database.PokemonDatabase
import com.aim2u.pokedexapp.data.model.PokemonEntity
import com.aim2u.pokedexapp.data.model.PokemonModel
import com.aim2u.pokedexapp.data.model.Result
import com.aim2u.pokedexapp.data.network.ApiService
import com.aim2u.pokedexapp.data.repository.PokemonRepository
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
    private val pokemonRepository: PokemonRepository

    val allPokemon: LiveData<List<PokemonEntity>> // LiveData in ViewModel

    init {
        val database = PokedexApplication.getInstance().database
        val pokemonDao = database.pokemonDao()
        pokemonRepository = PokemonRepository(pokemonDao)

        allPokemon = pokemonRepository.allPokemon
//            Transformations.map(pokemonRepository.allPokemon) { pokemonEntities ->
//                // Map each PokemonEntity to a Result
//                pokemonEntities.mapIndexed { index, pokemonEntity ->
//                    Result(name = pokemonEntity.name, url = pokemonEntity.url) // Adjust this as per your entity structure
//                }



//            .value.mapIndexed { index, result ->
//            PokemonEntity(index, result.name, result.url)
//        }
    }

    val pokemonList: LiveData<List<Result>> = liveData {
        val response = apiService.getPokemonList()
        Log.d("pokemon response","$response")
        // Simpan data ke database
        val pokemonList = response.results.mapIndexed { index, result ->
            PokemonEntity(index, result.name, result.url)
        }
        pokemonRepository.insertPokemon(pokemonList)
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