package com.aim2u.pokedexapp.ui.composable.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.aim2u.pokedexapp.ui.state_holder.PokemonViewModel
import com.aim2u.pokedexapp.data.model.Result
import com.aim2u.pokedexapp.ui.composable.screen.HomePage

@Composable
fun PokemonListScreen(viewModel: PokemonViewModel, navigateToDetail: (String) -> Unit) {
    val pokemonList by viewModel.pokemonList.observeAsState(emptyList())

    LazyColumn {
        items(items = pokemonList) { pokemon ->
            PokemonListItem(
                pokemon = pokemon,
                onItemClick = { navigateToDetail(pokemon.name) }
            )
        }
    }
}

@Composable
fun PokemonListItem(pokemon: Result, onItemClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick)
    ) {
        Text(pokemon.name)
        // Display Pokemon image and name here
    }
}

@Composable
fun PokemonDetailScreen(pokemonName: String) {
    HomePage("Majid")
}