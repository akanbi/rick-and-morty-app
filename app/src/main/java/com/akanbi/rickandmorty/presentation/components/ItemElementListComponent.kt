package com.akanbi.rickandmorty.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.akanbi.rickandmorty.R
import com.akanbi.rickandmorty.domain.model.Character

@Composable
fun ItemElementListComponent(character: Character) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp
    ) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(character.image)
                    .crossfade(enable = true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.ic_launcher_background)
            )
            Column(
                modifier = Modifier
                    .align(CenterVertically)
                    .padding(8.dp)
            ) {
                Text(
                    text = character.name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = character.location,
                    fontSize = 8.sp,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = character.species,
                    fontSize = 8.sp,
                    overflow = TextOverflow.Ellipsis
                )
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemElementListComponentPreview() {
    ItemElementListComponent(
        Character(
            1,
            "Rick Sanchez",
            "Male",
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            "url",
            "Human",
            "Citadel of Ricks"
        )
    )
}