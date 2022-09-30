package com.akanbi.rickandmorty.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akanbi.rickandmorty.domain.model.Character

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridListComponent(elements: List<Character>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        state = rememberLazyListState(),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(elements) {
            ItemElementListComponent(it)
        }
    }
}

@Preview
@Composable
fun GridListPreview() {
    GridListComponent(elements = charactersSample)
}

val charactersSample = listOf(
    Character(1, "Rick Sanchez", "Man", "https://rickandmortyapi.com/api/character/avatar/1.jpeg", "url", "Human", "Citadel Rickys"),
    Character(2, "Morty", "Man", "https://rickandmortyapi.com/api/character/avatar/2.jpeg", "url", "Human", "Earth"),
    Character(3, "Cenobite", "Man", "https://rickandmortyapi.com/api/character/avatar/747.jpeg", "url", "Mythological Creature", "Unknown"),
    Character(4, "Mr. Cookie President", "Alien", "https://rickandmortyapi.com/api/character/avatar/788.jpeg", "url", "Alien", "Replacement Dimension"),
    Character(5, "Fascist Shrimp Rick", "Man", "https://rickandmortyapi.com/api/character/avatar/503.jpeg", "url", "Animal", "Earth (Fascist Shrimp Dimension)")
)