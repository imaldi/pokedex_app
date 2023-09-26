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
import com.aim2u.pokedexapp.ui.composable.navigation.AppNavigation
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
            PokedexAppTheme {
                AppNavigation()
            }
        }


    }
}



