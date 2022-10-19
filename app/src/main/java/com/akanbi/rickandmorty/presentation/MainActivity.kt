package com.akanbi.rickandmorty.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.akanbi.rickandmorty.common.presentation.observerEvent
import com.akanbi.rickandmorty.domain.model.Character
import com.akanbi.rickandmorty.navigation.*
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
        setContent {
            val navController = rememberNavController()
            BuildNavHost(navController = navController)

            characterViewModel.list()
            setupObservers(navController)
        }
    }

    @Composable
    private fun BuildNavHost(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = SplashScreenDestination.route
        ) {
            composable(route = SplashScreenDestination.route) {
                SetupSplashScreen()
            }
            composable(route = CharacterDestination.route) {
                SetupCharacterScreen(navController)
            }
            composable(route = EpisodeDestination.route) {
                SplashScreenComponent()
            }
            composable(route = LocationDestination.route) {

            }
            composable(route = CharacterDetailsDestination.route) {
                val characterSelected = navController
                    .previousBackStackEntry?.arguments?.getParcelable<Character>(
                        CharacterDetailsDestination.characterSelectedArg
                    )
                if (characterSelected != null) {
                    CharacterDetailsScreen(navController = navController, character = characterSelected)
                }
            }
        }
    }

    @Composable
    private fun SetupSplashScreen() {
        RickAndMortyTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                SplashScreenComponent()
            }
        }
    }

    private fun setupObservers(navController: NavHostController) = with(characterViewModel) {
        characterList.observerEvent(
            this@MainActivity,
            onLoading = {
                isRefreshing = true
            },
            onSuccess = {
                clearBackStack(navController)
                characters.addAll(it)
                navController.navigateToCharacterScreen()
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

    private fun clearBackStack(navController: NavHostController) {
        if (characters.isEmpty()) navController.popBackStack()
    }

    @Composable
    private fun SetupCharacterScreen(navController: NavHostController) {
        CharacterScreen(
            navController = navController,
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
                navController.navigateToCharacterSelected(it)
            }
        )
    }

//    private fun setupCharacterDetailsScreen(character: Character) {
//        setContent {
//            CharacterDetailsScreen(character = character)
//        }
//    }

}