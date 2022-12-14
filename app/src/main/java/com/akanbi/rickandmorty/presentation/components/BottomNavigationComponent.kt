package com.akanbi.rickandmorty.presentation.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.akanbi.rickandmorty.R
import com.akanbi.rickandmorty.navigation.*

@Composable
fun BottomNavigationComponent(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(id = R.string.character))
            },
            selected = false,
            onClick = {
                popBackStackWhenCharacterDetailsRoute(navController)
                navController.navigateToCharacterScreen()
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(id = R.string.episode))
            },
            selected = false,
            onClick = {
                navController.navigateToEpisodeScreen()
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(id = R.string.location))
            },
            selected = false,
            onClick = {
                popBackStackWhenCharacterDetailsRoute(navController)
                navController.navigateToLocationScreen()
            }
        )
    }
}

private fun popBackStackWhenCharacterDetailsRoute(navController: NavHostController) {
    if (navController.isRouteBy(CharacterDetailsDestination.route))
        navController.popBackStack()
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    BottomNavigationComponent(rememberNavController())
}