package com.akanbi.rickandmorty.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.akanbi.rickandmorty.domain.model.Character

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

fun NavHostController.navigateToHome(route: String) =
    this.navigate(route = route) {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
        graph.setStartDestination(route)
    }

fun NavHostController.navigateToCharacterSelected(characterSelected: Character) {
    this.currentBackStackEntry?.arguments?.apply {
        putParcelable(CharacterDetailsDestination.characterSelectedArg, characterSelected)
    }
    this.navigateSingleTopTo(CharacterDetailsDestination.route)
}

fun NavHostController.navigateToCharacterScreen() = this.navigateSingleTopTo(CharacterDestination.route)

fun NavHostController.navigateToEpisodeScreen() = this.navigateSingleTopTo(EpisodeDestination.route)

fun NavHostController.navigateToLocationScreen() = this.navigateSingleTopTo(LocationDestination.route)