package com.akanbi.rickandmorty.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akanbi.rickandmorty.domain.model.LocationModel

@Composable
fun LocationList(
    modifier: Modifier = Modifier,
    locations: List<LocationModel>
) {
    LazyColumn(
        modifier = modifier,
        state = rememberLazyListState(),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(locations) { currentLocation ->
            LocationElement(currentLocation)
        }
    }
}

@Composable
fun LocationElement(location: LocationModel) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = location.name,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Text(text = location.type)
            Text(text = location.dimension)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {
                Text(text = "View residents")
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = ""
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LocationElementPreview() {
    LocationElement(
        LocationModel(
            id = 1,
            name = "Earth",
            type = "Planet",
            dimension = "Dimension C-137",
            created = "2017-11-10T12:42:04.162Z",
            url = "",
            residents = listOf()
        )
    )
}

@Preview(showBackground = true)
@Composable
fun LocationListPreview() {
    LocationList(
        locations = listOf(
            LocationModel(
                id = 1,
                name = "Earth",
                type = "Planet",
                dimension = "Dimension C-137",
                created = "2017-11-10T12:42:04.162Z",
                url = "",
                residents = listOf()
            ),
            LocationModel(
                id = 1,
                name = "Earth",
                type = "Planet",
                dimension = "Dimension C-137",
                created = "2017-11-10T12:42:04.162Z",
                url = "",
                residents = listOf()
            ),
            LocationModel(
                id = 1,
                name = "Earth",
                type = "Planet",
                dimension = "Dimension C-137",
                created = "2017-11-10T12:42:04.162Z",
                url = "",
                residents = listOf()
            ),
            LocationModel(
                id = 1,
                name = "Earth",
                type = "Planet",
                dimension = "Dimension C-137",
                created = "2017-11-10T12:42:04.162Z",
                url = "",
                residents = listOf()
            ),
            LocationModel(
                id = 1,
                name = "Earth",
                type = "Planet",
                dimension = "Dimension C-137",
                created = "2017-11-10T12:42:04.162Z",
                url = "",
                residents = listOf()
            )
        )
    )
}