package com.akanbi.rickandmorty.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import com.akanbi.rickandmorty.common.idsToListByUrl
import com.akanbi.rickandmorty.domain.model.LocationModel
import com.akanbi.rickandmorty.presentation.components.BottomNavigationComponent
import com.akanbi.rickandmorty.presentation.components.LocationList
import com.akanbi.rickandmorty.presentation.location.LocationViewModel
import kotlinx.coroutines.launch

@Composable
fun LocationScreen(
    navController: NavHostController,
    locationViewModel: LocationViewModel
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val state by locationViewModel.state.collectAsState()
    when {
        state.isLoading -> {
            Scaffold(
                bottomBar = { BottomNavigationComponent(navController) }
            ) { padding ->
                LoadingScreen(modifier = Modifier.padding(padding))
            }
        }
        state.isError -> {
            ErrorScreen()
        }
        state.locations.isNotEmpty() || state.residents.isNotEmpty() -> {
            Scaffold(
                bottomBar = { BottomNavigationComponent(navController) }
            ) { padding ->
                LocationList(
                    modifier = Modifier.padding(padding),
                    locations = state.locations,
                    onShowResidents = {
                        lifecycleScope.launch {
                            locationViewModel.fetchResidentsByLocation(idsToListByUrl(it.residents))
                        }
                    },
                    residents = state.residents
                )
            }
        }
    }
}