package com.akanbi.rickandmorty.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.akanbi.rickandmorty.R
import com.akanbi.rickandmorty.domain.model.Character
import com.akanbi.rickandmorty.presentation.character.CharacterDetailsViewModel
import com.akanbi.rickandmorty.presentation.components.BottomNavigationComponent
import com.akanbi.rickandmorty.presentation.components.EpisodeListComponent
import com.akanbi.rickandmorty.presentation.components.model.SimpleElement
import com.akanbi.rickandmorty.presentation.theme.BackgroundColor

@Composable
fun CharacterDetailsScreen(
    navController: NavHostController,
    detailsViewModel: CharacterDetailsViewModel
) {
    val state by detailsViewModel.state.collectAsState()

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
        state.episodes.isNotEmpty() -> {
            SuccessState(
                navController = navController,
                character = state.characterSelected,
                simpleElementList = state.episodes as MutableList<SimpleElement>
            )
        }
    }

}

@Composable
private fun SuccessState(
    navController: NavHostController,
    character: Character?,
    simpleElementList: MutableList<SimpleElement>
) {
    Scaffold(
        bottomBar = { BottomNavigationComponent(navController) }
    ) { padding ->
        BuildCharacterDetailsScreen(
            character = character!!,
            simpleElements = simpleElementList,
            modifier = Modifier
                .background(color = BackgroundColor)
                .padding(padding)
        )
    }
}

@Composable
fun BuildCharacterDetailsScreen(
    character: Character,
    simpleElements: MutableList<SimpleElement>,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .background(color = BackgroundColor)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 200.dp)
                .background(brush = Brush.horizontalGradient(listOf(Color.Green, Color.Yellow)))
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(character.image)
                    .crossfade(enable = true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder_rickandmorty),
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Text(
            text = character.name,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        StatusCharacter(status = character.status)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            CharacterElementComponent(
                title = character.species,
                subtitle = stringResource(id = R.string.species)
            )
            CharacterElementComponent(
                title = character.gender,
                subtitle = stringResource(id = R.string.gender),
                iconOnTitle = {
                    Icon(
                        painter = setGenderIcon(gender = character.gender),
                        contentDescription = null,
                        modifier = Modifier
                            .size(25.dp)
                            .padding(start = 6.dp)
                            .align(CenterVertically)
                    )

                }
            )
        }
        BuildEpisodeList(simpleElements)
    }
}

@Composable
private fun BuildEpisodeList(simpleElements: MutableList<SimpleElement>) {
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp
    ) {
        EpisodeListComponent(list = simpleElements)
    }
}

@Composable
fun CharacterElementComponent(
    title: String,
    subtitle: String,
    iconOnTitle: @Composable () -> Unit = {}
) {
    Column(
        modifier = Modifier.padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
            iconOnTitle()
        }
        Text(
            text = subtitle
        )
    }

}

@Composable
fun StatusCharacter(
    status: String
) {
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = setStatusIcon(status = status),
            contentDescription = null,
            modifier = Modifier
                .heightIn(max = 20.dp)
                .widthIn(max = 20.dp)
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = status
        )
    }
}

@Composable
fun setStatusIcon(status: String) =
    when (status) {
        "Alive" -> {
            painterResource(id = R.drawable.alive_icon)
        }
        "Dead" -> {
            painterResource(id = R.drawable.dead_icon)
        }
        else -> {
            painterResource(id = R.drawable.unknown_icon)
        }
    }

@Composable
fun setGenderIcon(gender: String) =
    when (gender) {
        "Male" -> {
            painterResource(id = R.drawable.male_icon)
        }
        "Female" -> {
            painterResource(id = R.drawable.female_icon)
        }
        else -> {
            painterResource(id = R.drawable.transgender)
        }
    }

@Preview(showBackground = true)
@Composable
fun StatusCharacterPreview() {
    StatusCharacter(status = "Alive")
}

@Preview(showBackground = true)
@Composable
fun CardSimplePreview() {
    CharacterElementComponent(title = "Human", subtitle = "Species")
}

//@Preview(showBackground = true)
//@Composable
//fun CharacterDetailsScreenPreview() {
//    CharacterDetailsScreen(
//        navController = rememberNavController(),
//    )
//}