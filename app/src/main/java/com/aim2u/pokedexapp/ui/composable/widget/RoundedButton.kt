package com.aim2u.pokedexapp.ui.composable.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RoundedButton(
    text: String,
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(16.dp),
    shape: Shape = RoundedCornerShape(8.dp),
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .clickable { onClick() },
        shape = shape,
        color = Color.White,
    ) {
        Row(
            modifier = Modifier
                .padding(padding)
                .wrapContentWidth()
                .wrapContentHeight()
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
@Preview
@Composable
fun RoundedButtonPreview() {
    RoundedButton(text = "Start", modifier = Modifier.padding(16.dp)) {
        // Handle button click
    }
}