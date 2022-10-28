package com.akanbi.rickandmorty.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.akanbi.rickandmorty.presentation.components.BottomNavigationComponent
import com.akanbi.rickandmorty.presentation.components.LocationList
import com.akanbi.rickandmorty.presentation.location.LocationViewModel

@Composable
fun LocationScreen(
    navController: NavHostController,
    locationViewModel: LocationViewModel
) {
    val state by locationViewModel.state.collectAsState()
    when {
        state.isLoading -> {
            LoadingScreen()
        }
        state.isError -> {
            ErrorScreen()
        }
        state.locations.isNotEmpty() -> {
            Scaffold(
                bottomBar = { BottomNavigationComponent(navController) }
            ) { padding ->
                LocationList(
                    modifier = Modifier.padding(padding),
                    locations = state.locations
                )
            }
        }
    }
}