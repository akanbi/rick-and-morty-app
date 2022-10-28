package com.akanbi.rickandmorty.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.akanbi.rickandmorty.R

@Composable
fun LoadingScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.5f)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val mortyComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.morty_loader))
        val loadingComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
        LottieAnimation(
            mortyComposition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier
                .size(200.dp)
        )
        LottieAnimation(
            loadingComposition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier
                .size(80.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoadingScreenReview() {
    LoadingScreen()
}