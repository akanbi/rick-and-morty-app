package com.akanbi.rickandmorty.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.rememberSwipeableState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akanbi.rickandmorty.domain.model.Character
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import timber.log.Timber

@Composable
fun GridListComponent(
    modifier: Modifier = Modifier,
    elements: List<Character>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onPagination: () -> Unit
) {
    SwipeRefresh(
        modifier = modifier.fillMaxWidth(),
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = {
            onRefresh()
        }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = rememberLazyGridState(),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(elements) { index: Int, item: Character ->
                if (index == elements.size - 1) {
                    Timber.d("Pagination - position: $index list size: ${elements.size - 1}")
                    onPagination()
                } else
                    ItemElementListComponent(character = item)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GridListPreview() {
    GridListComponent(elements = charactersSample, isRefreshing = false, onRefresh = {}, onPagination = {})
}

val charactersSample = mutableListOf(
    Character(
        1,
        "Rick Sanchez",
        "Man",
        "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        "url",
        "Human",
        "Citadel Rickys"
    ),
    Character(
        2,
        "Morty",
        "Man",
        "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
        "url",
        "Human",
        "Earth"
    ),
    Character(
        3,
        "Cenobite",
        "Man",
        "https://rickandmortyapi.com/api/character/avatar/747.jpeg",
        "url",
        "Mythological Creature",
        "Unknown"
    ),
    Character(
        4,
        "Mr. Cookie President",
        "Alien",
        "https://rickandmortyapi.com/api/character/avatar/788.jpeg",
        "url",
        "Alien",
        "Replacement Dimension"
    ),
    Character(
        5,
        "Fascist Shrimp Rick",
        "Man",
        "https://rickandmortyapi.com/api/character/avatar/503.jpeg",
        "url",
        "Animal",
        "Earth (Fascist Shrimp Dimension)"
    )
)