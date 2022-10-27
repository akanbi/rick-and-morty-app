package com.akanbi.rickandmorty.presentation.screen.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.akanbi.rickandmorty.domain.model.Character
import com.akanbi.rickandmorty.navigation.navigateToCharacterScreen
import com.akanbi.rickandmorty.presentation.character.CharacterUIEvent
import com.akanbi.rickandmorty.presentation.character.CharacterViewModel
import com.akanbi.rickandmorty.presentation.components.BottomNavigationComponent
import com.akanbi.rickandmorty.presentation.components.GridListComponent
import com.akanbi.rickandmorty.presentation.components.SearchBar
import com.akanbi.rickandmorty.presentation.components.charactersSample
import com.akanbi.rickandmorty.presentation.screen.ErrorScreen
import com.akanbi.rickandmorty.presentation.theme.BackgroundColor

@Composable
fun CharacterScreen(
    navController: NavHostController,
    onItemSelected: (character: Character) -> Unit,
    characterViewModel: CharacterViewModel,
) {
    var characters = mutableListOf<Character>()
    val state by characterViewModel.state.collectAsState()
    Scaffold(
        bottomBar = { BottomNavigationComponent(navController) }
    ) { padding ->
        when {
//            state.isLoading -> {
//                CharacterList(
//                    modifier = Modifier
//                        .background(color = BackgroundColor)
//                        .padding(padding),
//                    elements = characters,
//                    isRefreshing = state.isRefreshing,
//                    onRefresh = { sendEventToOnRefresh(characterViewModel) },
//                    onPagination = { sendEventToOnPagination(characterViewModel) },
//                    onItemSelected = { onItemSelected(it) }
//                )
//            }
            state.isError -> {
                characters = mutableListOf()
                ErrorScreen()
            }
            state.isPagination -> {
                characterViewModel.list()
            }
            state.isRefreshing -> {
                characters = mutableListOf()
                characterViewModel.refreshList()
//                CharacterList(
//                    modifier = Modifier
//                        .background(color = BackgroundColor)
//                        .padding(padding),
//                    elements = characters,
//                    isRefreshing = state.isRefreshing,
//                    onRefresh = { sendEventToOnRefresh(characterViewModel) },
//                    onPagination = { sendEventToOnPagination(characterViewModel) },
//                    onItemSelected = { onItemSelected(it) }
//                )
            }
            state.characters.isNotEmpty() -> {
//                clearBackStack(navController, characters)
                characters.addAll(state.characters)
                CharacterList(
                    modifier = Modifier
                        .background(color = BackgroundColor)
                        .padding(padding),
                    elements = characters,
                    isRefreshing = state.isRefreshing,
                    onRefresh = { sendEventToOnRefresh(characterViewModel) },
                    onPagination = { sendEventToOnPagination(characterViewModel) },
                    onItemSelected = { onItemSelected(it) }
                )
            }
        }

    }
}

private fun clearBackStack(navController: NavHostController, characters: MutableList<Character>) {
    if (characters.isEmpty()) navController.popBackStack()
}

    fun sendEventToOnPagination(characterViewModel: CharacterViewModel) {
    characterViewModel.sendEvent(
        CharacterUIEvent.OnPagination(
            isPagination = true,
            isRefreshing = true,
            isLoading = false,
            characters = characterViewModel.state.value.characters
        ))
}

fun sendEventToOnRefresh(characterViewModel: CharacterViewModel) {
    characterViewModel.sendEvent(
        CharacterUIEvent.OnRefresh(
            isPagination = false,
            isRefreshing = true,
            isLoading = false,
            characters = characterViewModel.state.value.characters
        ))
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
        GridListComponent(
            elements = elements,
            isRefreshing = isRefreshing,
            onRefresh = { onRefresh() },
            onPagination = { onPagination() },
            onItemSelected = { onItemSelected(it) }
        )
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun CharacterScreenPreview() {
//    CharacterScreen(
//        rememberNavController(),
//        elements = charactersSample,
//        false,
//        {},
//        {},
//        {}
//    )
//}