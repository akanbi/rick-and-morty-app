package com.akanbi.rickandmorty.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.akanbi.rickandmorty.R
import com.akanbi.rickandmorty.presentation.components.model.Residents

@Composable
fun ResidentsGrid(residentsUrl: List<Residents>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        state = rememberLazyGridState(),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        itemsIndexed(residentsUrl) { index: Int, item: Residents ->
            ResidentsElement(item)
        }
    }
}

@Composable
fun ResidentsElement(resident: Residents) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(resident.imageUrl)
                    .crossfade(enable = true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder_rickandmorty),
                modifier = Modifier.size(80.dp)
            )
            Row(
                modifier = Modifier
                    .offset(y = 69.dp)
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.4f)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = resident.name,
                    fontSize = 8.sp,
                    textAlign = TextAlign.End,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResidentsElementPreview() {
    ResidentsElement(Residents("Ricky Sanches", ""))
}

@Preview(showBackground = true)
@Composable
fun ResidentsGridPreview() {
    ResidentsGrid(
        residentsUrl = listOf(
            Residents("Ricky", ""),
            Residents("Morty", ""),
            Residents("Summer", ""),
            Residents("Beth", ""),
            Residents("Ricky", ""),
            Residents("Ricky", ""),
            Residents("Ricky", ""),
            Residents("Ricky", ""),
            Residents("Ricky", "")
        )
    )
}