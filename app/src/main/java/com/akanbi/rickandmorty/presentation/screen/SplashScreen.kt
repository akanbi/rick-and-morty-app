package com.akanbi.rickandmorty.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.akanbi.rickandmorty.R
import com.akanbi.rickandmorty.navigation.navigateToCharacterScreen
import com.akanbi.rickandmorty.presentation.components.GifImage
import com.akanbi.rickandmorty.presentation.splash.SplashScreenViewModel

@Composable
fun SplashScreenComponent(
    navController: NavHostController,
    splashScreenViewModel: SplashScreenViewModel
) {
    val state by splashScreenViewModel.state.collectAsState()
    when {
        state.isLoading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.padding(bottom = 8.dp),
                    painter = painterResource(id = R.drawable.ricky_and_morty_logo),
                    contentDescription = null
                )
                LinearProgressIndicator()
            }
        }
        state.showCharacterListScreen -> {
            navController.navigateToCharacterScreen()
        }
    }


}