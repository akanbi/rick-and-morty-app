package com.akanbi.rickandmorty.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akanbi.rickandmorty.R
import com.akanbi.rickandmorty.presentation.theme.Purple200
import com.akanbi.rickandmorty.presentation.theme.Purple700

@Composable
fun ErrorScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(Color.Green, Color.Yellow)
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.rick_and_morty_scared),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
        Text(text = "OPS! ", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text(text = "Something went wrong, please try again later.", fontSize = 16.sp)
        // Implementar botão de retry
    }
}

@Preview(showSystemUi = true)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen()
}