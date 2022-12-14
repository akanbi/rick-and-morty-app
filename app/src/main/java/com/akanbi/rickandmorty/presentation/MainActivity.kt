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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.akanbi.rickandmorty.common.presentation.observerEvent
import com.akanbi.rickandmorty.di.characterDetailsViewModel
import com.akanbi.rickandmorty.domain.model.Character
import com.akanbi.rickandmorty.navigation.*
import com.akanbi.rickandmorty.presentation.character.CharacterViewModel
import com.akanbi.rickandmorty.presentation.location.LocationViewModel
import com.akanbi.rickandmorty.presentation.screen.*
import com.akanbi.rickandmorty.presentation.splash.SplashScreenViewModel
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
                val splashScreenViewModel = hiltViewModel<SplashScreenViewModel>()
                SetupSplashScreen(navController, splashScreenViewModel)
            }
            composable(route = CharacterDestination.route) {
                SetupCharacterScreen(navController)
            }
            composable(route = EpisodeDestination.route) {

            }
            composable(route = LocationDestination.route) {
                val locationViewModel = hiltViewModel<LocationViewModel>()
                LocationScreen(
                    navController = navController,
                    locationViewModel = locationViewModel
                )
            }
            composable(route = CharacterDetailsDestination.route) {
                val characterSelected = navController
                    .previousBackStackEntry?.arguments?.getParcelable<Character>(
                        CharacterDetailsDestination.characterSelectedArg
                    )
                if (characterSelected != null) {
                    val detailsViewModel = characterDetailsViewModel(
                        viewModelStoreOwner = it,
                        character = characterSelected
                    )
                    CharacterDetailsScreen(
                        navController = navController,
                        detailsViewModel = detailsViewModel
                    )
                }
            }
            composable(route = ErrorDestination.route) {
                ErrorScreen()
            }
            composable(route = LoadingDestination.route) {
                LoadingScreen()
            }
        }
    }

    @Composable
    private fun SetupSplashScreen(navController: NavHostController, splashScreenViewModel: SplashScreenViewModel) {
        RickAndMortyTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                SplashScreenComponent(navController, splashScreenViewModel)
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
                navController.navigateToErrorScreen()
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

}