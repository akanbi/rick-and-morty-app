package com.akanbi.rickandmorty.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

@Composable
fun SplashScreenComponent() {
    Box(modifier = Modifier.fillMaxSize()) {
        SubcomposeAsyncImage(
            modifier = Modifier.align(Alignment.Center),
//        model = "https://mir-s3-cdn-cf.behance.net/projects/max_808/2ee5d290690493.Y3JvcCw5MTUsNzE2LDc2OCwxODE.png",
            model = splashImage,
            loading = {
                CircularProgressIndicator()
            },
            contentDescription = null,
        )
        Text(
            modifier = Modifier.padding(bottom = 30.dp).align(Alignment.BottomCenter),
            text = "Ricky and Morty App", fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SplashScreenComponent()
}

var splashImage = "https://cdn.dribbble.com/users/4517867/screenshots/10757134/sequ_ncia_01.gif"