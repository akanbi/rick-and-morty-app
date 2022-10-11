package com.akanbi.rickandmorty.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akanbi.rickandmorty.presentation.components.model.SimpleElement

@Composable
fun SimpleListComponent(
    list: List<SimpleElement>
) {
    LazyColumn(
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(list) {
            SimpleElementComponent(title = it.title, subtitle = it.subtitle)
        }
    }
}

@Composable
fun SimpleElementComponent(
    title: String,
    subtitle: String,
    isIcon: Boolean = true,
    iconId: Int = 0
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BuildIconOnComponent(isIcon, iconId)
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(text = title, fontWeight = FontWeight.Bold)
                Text(text = subtitle)
            }

        }
    }
}

@Composable
private fun BuildIconOnComponent(
    isIcon: Boolean,
    iconId: Int
) {
    if (isIcon) {
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = null
        )
    } else {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SimpleElementPreview() {
    SimpleElementComponent(title = "S01E07", subtitle = "Rick and Morty Episode")

}

@Preview(showBackground = true)
@Composable
fun SimpleListComponentPreview() {
    SimpleListComponent(
        list = listOf(
            SimpleElement("S01E01", "Season 1 Episode 1"),
            SimpleElement("S01E02", "Season 1 Episode 2"),
            SimpleElement("S01E03", "Season 1 Episode 3"),
            SimpleElement("S02E01", "Season 2 Episode 1"),
            SimpleElement("S02E02", "Season 2 Episode 2")
        )
    )
}