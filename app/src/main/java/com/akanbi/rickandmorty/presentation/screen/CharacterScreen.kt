package com.akanbi.rickandmorty.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.akanbi.rickandmorty.domain.model.Character
import com.akanbi.rickandmorty.presentation.components.BottomNavigationComponent
import com.akanbi.rickandmorty.presentation.components.CharacterGridList
import com.akanbi.rickandmorty.presentation.components.SearchBar
import com.akanbi.rickandmorty.presentation.components.charactersSample
import com.akanbi.rickandmorty.presentation.theme.BackgroundColor

@Composable
fun CharacterScreen(
    navController: NavHostController,
    elements: MutableList<Character>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onPagination: () -> Unit,
    onItemSelected: (character: Character) -> Unit
) {
    Scaffold(
        bottomBar = { BottomNavigationComponent(navController) }
    ) { padding ->
        CharacterList(
            modifier = Modifier
                .background(color = BackgroundColor)
                .padding(padding),
            elements = elements,
            isRefreshing = isRefreshing,
            onRefresh = { onRefresh() },
            onPagination = { onPagination() },
            onItemSelected = { onItemSelected(it) }
        )
    }
}

@Composable
fun CharacterList(
    modifier: Modifier = Modifier,
    elements: MutableList<Character>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onPagination: () -> Unit,
    onItemSelected: (character: Character) -> Unit
) {
    Column(modifier = modifier) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        Spacer(Modifier.height(4.dp))
        CharacterGridList(
            elements = elements,
            isRefreshing = isRefreshing,
            onRefresh = { onRefresh() },
            onPagination = { onPagination() },
            onItemSelected = { onItemSelected(it) }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun CharacterScreenPreview() {
    CharacterScreen(
        rememberNavController(),
        elements = charactersSample,
        false,
        {},
        {},
        {}
    )
}