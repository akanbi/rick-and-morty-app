package com.akanbi.rickandmorty.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.akanbi.rickandmorty.domain.model.Character

interface RickAndMortyDestinations {
    val route: String
}

object CharacterDestination: RickAndMortyDestinations {
    override val route: String = "character"
}

object EpisodeDestination: RickAndMortyDestinations {
    override val route: String = "episode"
}

object LocationDestination: RickAndMortyDestinations {
    override val route: String = "location"
}

object ErrorDestination: RickAndMortyDestinations {
    override val route: String = "error"
}

object LoadingDestination: RickAndMortyDestinations {
    override val route: String = "loading"
}

object CharacterDetailsDestination: RickAndMortyDestinations {
    override val route: String = "character_details"
    val characterSelectedArg = "character_selected_arg"
    val routeWithArgs = "${route}/{${characterSelectedArg}}"
    val arguments = listOf(
        navArgument(characterSelectedArg) { type = NavType.ParcelableType(Character::class.java) }
    )
}

object SplashScreenDestination: RickAndMortyDestinations {
    override val route: String = "splash_screen"
}

val destinationScreens = listOf(
    CharacterDestination,
    EpisodeDestination,
    LocationDestination,
    CharacterDetailsDestination
)