package com.aim2u.pokedexapp.ui.composable.navigation

import android.app.Application
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aim2u.pokedexapp.ui.composable.screen.HomePage
import com.aim2u.pokedexapp.ui.composable.widget.PokemonDetailScreen
import com.aim2u.pokedexapp.ui.composable.widget.PokemonListScreen
import com.aim2u.pokedexapp.ui.state_holder.PokemonViewModel
import com.aim2u.pokemonapp.ui.composable.screen.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    //todo use "application" here
    val viewModel: PokemonViewModel = viewModel()
    var navigateToMain by remember { mutableStateOf(false) }
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navigateToMain = { navController.navigate("pokemonList") })
        }
        composable("home") {
            HomePage("Aldi")
        }
        composable("pokemonList") {
            PokemonListScreen(
                viewModel = viewModel,
                navigateToDetail = { pokemonName ->
                    navController.navigate("pokemonDetail/$pokemonName")
                }
            )
        }
        composable(
            "pokemonDetail/{pokemonName}",
            arguments = listOf(navArgument("pokemonName") { type = NavType.StringType })
        ) { backStackEntry ->
            val pokemonName = backStackEntry.arguments?.getString("pokemonName")
            if (pokemonName != null) {
                PokemonDetailScreen(viewModel = viewModel)
            }
        }
    }

    if (navigateToMain) {
        navController.navigate("home")
    }
}