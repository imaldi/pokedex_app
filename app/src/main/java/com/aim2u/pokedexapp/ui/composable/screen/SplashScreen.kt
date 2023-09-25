package com.aim2u.pokemonapp.ui.composable.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aim2u.pokedexapp.R
import com.aim2u.pokedexapp.ui.composable.widget.RoundedButton
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navigateToMain: () -> Unit
) {
    val context = LocalContext.current
    // Replace with your splash screen layout
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,){
            Text(text = "Pokedex", fontSize = 32.sp, fontWeight = FontWeight.Bold)
            Image(
                painter = painterResource(id = R.drawable.pokeball),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
            RoundedButton(text = "Start", modifier = Modifier.padding(16.dp)) {

                Toast.makeText(context, "Let's Go!!!", Toast.LENGTH_SHORT).show()

            }
        }
    }

    // Simulate a delay and then navigate to the main screen
    LaunchedEffect(key1 = true) {
        delay(2000) // Adjust the delay duration as needed
        navigateToMain()
    }
}