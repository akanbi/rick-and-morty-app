package com.akanbi.rickandmorty.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.akanbi.rickandmorty.common.presentation.observerEvent
import com.akanbi.rickandmorty.domain.model.Character
import com.akanbi.rickandmorty.presentation.character.CharacterViewModel
import com.akanbi.rickandmorty.presentation.screen.CharacterDetailsScreen
import com.akanbi.rickandmorty.presentation.screen.CharacterScreen
import com.akanbi.rickandmorty.presentation.screen.ErrorScreen
import com.akanbi.rickandmorty.presentation.screen.SplashScreenComponent
import com.akanbi.rickandmorty.presentation.theme.RickAndMortyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val characterViewModel: CharacterViewModel by viewModels()
    private var isRefreshing: Boolean = false
    private var characters: MutableList<Character> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSplashScreen()

        characterViewModel.list()
        setupObservers()
    }

    private fun setupSplashScreen() {
        setContent {
            RickAndMortyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SplashScreenComponent()
                }
            }
        }
    }

    private fun setupObservers() = with(characterViewModel) {
        characterList.observerEvent(
            this@MainActivity,
            onLoading = {
                isRefreshing = true
            },
            onSuccess = {
                characters.addAll(it)
                setContent {
                    SetupCharacterScreen()
                }
                isRefreshing = false
            },
            onError = {
                characters = mutableListOf()
                setContent {
                    ErrorScreen()
                }
            }
        )
        swipeRefresh.observe(this@MainActivity) {
            isRefreshing = it
        }
    }

    @Composable
    private fun SetupCharacterScreen() {
        CharacterScreen(
            elements = characters,
            isRefreshing = isRefreshing,
            onRefresh = {
                characters = mutableListOf()
                characterViewModel.refreshList()
            },
            onPagination = {
                characterViewModel.list()
            },
            onItemSelected = {
                setupCharacterDetailsScreen(character = it)
            }
        )
    }

    private fun setupCharacterDetailsScreen(character: Character) {
        setContent { 
            CharacterDetailsScreen(character = character)
        }
    }

}