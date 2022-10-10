package com.akanbi.rickandmorty.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.akanbi.rickandmorty.R
import com.akanbi.rickandmorty.domain.model.Character
import com.akanbi.rickandmorty.presentation.components.charactersSample
import com.akanbi.rickandmorty.presentation.theme.BackgroundColor

@Composable
fun CharacterDetailsScreen(
    character: Character
) {
    Column(
        modifier = Modifier.background(color = BackgroundColor).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 200.dp)
                .background(brush = Brush.horizontalGradient(listOf(Color.Green, Color.Yellow)))
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(character.image)
                    .crossfade(enable = true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder_rickandmorty),
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Text(
            text = character.name,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterDetailsScreenPreview() {
    CharacterDetailsScreen(character = charactersSample[0])
}