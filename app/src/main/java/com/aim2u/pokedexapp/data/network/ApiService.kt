package com.aim2u.pokedexapp.data.network

import com.aim2u.pokedexapp.data.model.Pokemon
import com.aim2u.pokedexapp.data.model.PokemonModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon")
    suspend fun getPokemonList(): Pokemon

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String): PokemonModel

    companion object {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}