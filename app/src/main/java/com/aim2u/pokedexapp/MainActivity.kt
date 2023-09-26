package com.aim2u.pokedexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aim2u.pokedexapp.ui.composable.screen.HomePage
import com.aim2u.pokedexapp.ui.theme.PokedexAppTheme
import com.aim2u.pokemonapp.ui.composable.screen.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var navigateToMain by remember { mutableStateOf(false) }

            PokedexAppTheme {
                if (navigateToMain) {
                    HomePage("Aldi")
                } else {
                    SplashScreen(
                        navigateToMain = { navigateToMain = true }
                    )
                }
            }
        }


    }
}



