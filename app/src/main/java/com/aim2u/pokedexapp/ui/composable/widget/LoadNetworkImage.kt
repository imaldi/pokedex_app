package com.aim2u.pokedexapp.ui.composable.widget

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.aim2u.pokedexapp.R

@Composable
fun LoadNetworkImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    val painter = // You can customize image loading options here if needed
        rememberAsyncImagePainter(
            ImageRequest.Builder // Placeholder image while loading
        // Image to display on error
            (LocalContext.current).data(data = url).apply(block = fun ImageRequest.Builder.() {
            placeholder(R.drawable.pokeball) // Placeholder image while loading
            error(R.drawable.pokeball) // Image to display on error
        }).build()
        )

    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = ContentScale.Crop,
        alpha = if (painter.state is AsyncImagePainter.State.Success) 1f else 0.5f // Fade in the image when loaded
    )
}