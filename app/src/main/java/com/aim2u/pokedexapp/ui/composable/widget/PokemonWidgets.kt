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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aim2u.pokedexapp.ui.state_holder.PokemonViewModel
import com.aim2u.pokedexapp.data.model.Result
import com.aim2u.pokedexapp.utils.ConnectivityUtil


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(viewModel: PokemonViewModel, navigateToDetail: (String) -> Unit) {
    var context = LocalContext.current

    lateinit var selectedData: List<Result>
    val pokemonList by viewModel.pokemonList.observeAsState(emptyList())
    val pokemonListDB by viewModel.allPokemon.observeAsState(emptyList())
    if (ConnectivityUtil.isOnline(context)) {
        selectedData = pokemonList
    } else {
        selectedData = pokemonListDB.mapIndexed { index, pokemonEntity ->
            Result(name = pokemonEntity.name, url = pokemonEntity.imageUrl) // Adjust this as per your entity structure
        }
    }

    val searchText = remember { mutableStateOf("") }
    val isAscending = remember { mutableStateOf(true) }
    val filteredPokemonList = filterAndSortPokemonList(selectedData, searchText.value, isAscending.value)

    Box(modifier = Modifier.padding(16.dp)) {
        LazyColumn {
            item {
                // FIXME cek kenapa perlu opt in / experimental nanti
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = searchText.value,
                    onValueChange = { newQuery ->
                        searchText.value = newQuery
                    },
                    placeholder = {
                        Text(text = "Search Pokemon")
                    }
                )
            }

            item {
                Box {
                    Button(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(end = 16.dp, bottom = 16.dp),
                        onClick = {
                            isAscending.value = !isAscending.value
                        }
                    ) {
                        Text(text = if (isAscending.value) "Sort A-Z" else "Sort Z-A")
                    }
                }
            }
            items(items = filteredPokemonList) { pokemon ->
                PokemonListItem(
                    pokemon = pokemon,
                    onItemClick = {
                        viewModel.fetchPokemonByName(pokemon.name)
                        navigateToDetail(pokemon.name)
                    }
                )
            }
        }
    }
}
private fun filterAndSortPokemonList(
    pokemonList: List<Result>,
    searchQuery: String,
    isAscending: Boolean
): List<Result> {
    // Filter the list based on the search query
    val filteredList = if (searchQuery.isNotBlank()) {
        pokemonList.filter { it.name.contains(searchQuery, ignoreCase = true) }
    } else {
        pokemonList
    }

    // Sort the filtered list
    val sortedList = if (isAscending) {
        filteredList.sortedBy { it.name }
    } else {
        filteredList.sortedByDescending { it.name }
    }

    return sortedList
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
    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp), color = Color.White) {
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

