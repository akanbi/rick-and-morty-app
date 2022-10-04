package com.akanbi.rickandmorty.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.akanbi.rickandmorty.common.presentation.observerEvent
import com.akanbi.rickandmorty.domain.model.Character
import com.akanbi.rickandmorty.presentation.character.CharacterViewModel
import com.akanbi.rickandmorty.presentation.components.charactersSample
import com.akanbi.rickandmorty.presentation.screen.HomeScreen
import com.akanbi.rickandmorty.presentation.theme.RickAndMortyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val characterViewModel: CharacterViewModel by viewModels()
    private var isRefreshing: Boolean = false
    private var characters: MutableList<Character> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        characterViewModel.list()
        setupObservers()

        setContent {
            RickAndMortyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeScreen(
                        elements = characters,
                        isRefreshing = isRefreshing,
                        onRefresh = { characterViewModel.refreshList() },
                        onPagination = { characterViewModel.list() }
                    )
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
                    HomeScreen(
                        elements = characters,
                        isRefreshing = isRefreshing,
                        onRefresh = {
                            characters.clear()
                            characterViewModel.refreshList()
                        },
                        onPagination = {
                            characterViewModel.list()
                        }
                    )
                }
            },
            onError = {}
        )
        swipeRefresh.observe(this@MainActivity) {
            isRefreshing = it
        }
    }

}

