package com.akanbi.rickandmorty.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.akanbi.rickandmorty.presentation.theme.RickAndMortyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SplashScreenComponent()
                }
            }
        }
    }
}

@Composable
fun SplashScreenComponent() {
    SubcomposeAsyncImage(
//        model = "https://mir-s3-cdn-cf.behance.net/projects/max_808/2ee5d290690493.Y3JvcCw5MTUsNzE2LDc2OCwxODE.png",
        model = "https://cdn.dribbble.com/users/4517867/screenshots/10757134/sequ_ncia_01.gif",
        loading = {
            CircularProgressIndicator()
        },
        contentDescription = "Blá"
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RickAndMortyTheme {
        SplashScreenComponent()
    }
}