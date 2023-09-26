package com.aim2u.pokedexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.aim2u.pokedexapp.ui.theme.PokedexAppTheme
import com.aim2u.pokemonapp.ui.composable.screen.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var navigateToMain by remember { mutableStateOf(false) }
            val navController = rememberNavController()


            PokedexAppTheme {
                val viewModel: PokemonViewModel = viewModel()
                NavHost(navController = navController, startDestination = "pokemonList") {
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
                            PokemonDetailScreen(pokemonName = pokemonName)
                        }
                    }
                }
//                if (navigateToMain) {
//                    HomePage("Aldi")
//                } else {
//                    SplashScreen(
//                        navigateToMain = { navigateToMain = true }
//                    )
//                }
            }
        }


    }
}



