package com.aim2u.pokedexapp.ui.composable.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aim2u.pokedexapp.ui.theme.PokedexAppTheme

@Composable
fun HomePage(name: String) {
    Surface(color = MaterialTheme.colorScheme.primary
    ) {
        CenteredText(
            text = "Hello $name!",
        )
    }
}

@Composable
fun CenteredText(text: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center).padding(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    PokedexAppTheme {
        HomePage("Android")
    }
}
