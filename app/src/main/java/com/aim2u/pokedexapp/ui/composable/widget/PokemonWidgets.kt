package com.aim2u.pokedexapp.ui.composable.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aim2u.pokedexapp.ui.state_holder.PokemonViewModel
import com.aim2u.pokedexapp.data.model.Result

@Composable
fun PokemonListScreen(viewModel: PokemonViewModel, navigateToDetail: (String) -> Unit) {
    val pokemonList by viewModel.pokemonList.observeAsState(emptyList())

    LazyColumn {
        items(items = pokemonList) { pokemon ->
            PokemonListItem(
                pokemon = pokemon,
                onItemClick = {
                    viewModel.fetchPokemonByName(pokemon.name)
                    navigateToDetail(pokemon.name) }
            )
        }
    }
}

@Composable
fun PokemonListItem(pokemon: Result, onItemClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
            .clickable(onClick = onItemClick),
        shadowElevation = 4.dp

    ) {
        Surface(
            color = Color.Green, // Predefined color
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable(onClick = onItemClick)
            ) {
                Text(pokemon.name, color = Color.Black)
                // Display Pokemon image and name here
            }
        }
    }
}

@Composable
fun PokemonDetailScreen(
    viewModel: PokemonViewModel) {
    val pokemonDetail by viewModel.pokemonDetail.collectAsState(null)
    val abilities = (pokemonDetail?.abilities?.map { it.ability.name } ?: emptyList()).toList()
    Surface(modifier = Modifier.fillMaxSize().padding(16.dp), color = Color.White) {
        LazyColumn{
            item {
                Text(text = pokemonDetail?.name?.capitalize(Locale.current) ?: "No Name", style = MaterialTheme.typography.headlineMedium)
            }
            item {
                Box {
                    Surface(modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp), color = Color.White) {
                        LoadNetworkImage(
                            url = pokemonDetail?.sprites?.frontDefault ?: "No Image", modifier = Modifier
                                .height(32.dp)
                                .width(32.dp)
                                .align(Alignment.Center)
                        )
                    }

                }
            }
            item {
                Text(text = "Abilities", style = MaterialTheme.typography.headlineMedium)
            }
            items(items = abilities) { ability ->
                Text("$ability")
            }
        }
    }
}

