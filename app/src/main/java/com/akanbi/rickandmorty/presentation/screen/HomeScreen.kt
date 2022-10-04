package com.akanbi.rickandmorty.presentation.screen

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.akanbi.rickandmorty.domain.model.Character
import com.akanbi.rickandmorty.presentation.components.GridListComponent
import com.akanbi.rickandmorty.presentation.components.charactersSample

@Composable
fun HomeScreen(
    elements: MutableList<Character>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onPagination: () -> Unit
) {
    Surface {
        GridListComponent(
            elements = elements,
            isRefreshing = isRefreshing,
            onRefresh = {
                onRefresh()
            },
            onPagination = onPagination
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(elements = charactersSample, false, {}) {}
}