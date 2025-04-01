package com.saiferwp.swplanetviewerdemo.planets.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saiferwp.swplanetviewerdemo.core.model.PlanetsResponse

@Composable
fun PlanetsListScreen(
    modifier: Modifier = Modifier,
    planetsList: List<PlanetsResponse.Planet>
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = planetsList) { planet ->
            PlanetsListCard(
                planet = planet
            )
        }
    }
}

@Composable
fun PlanetsListCard(
    modifier: Modifier = Modifier,
    planet: PlanetsResponse.Planet
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 2.dp,
        modifier = modifier
    ) {
        Column(modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)) {
            Text(
                text = planet.name,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = planet.climate,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = planet.population,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlanetsListScreenPreview() {
    MaterialTheme {
        PlanetsListScreen(planetsList = emptyList())
    }
}