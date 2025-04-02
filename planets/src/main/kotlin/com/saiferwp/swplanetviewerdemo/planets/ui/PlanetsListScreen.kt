package com.saiferwp.swplanetviewerdemo.planets.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import com.saiferwp.swplanetviewerdemo.core.ui.compose.ErrorView
import com.saiferwp.swplanetviewerdemo.core.ui.compose.LoadingView
import com.saiferwp.swplanetviewerdemo.core.ui.theme.SWPlanetViewerDemoTheme
import com.saiferwp.swplanetviewerdemo.planets.model.PlanetsListItem
import com.saiferwp.swplanetviewerdemo.planets.viewmodel.PlanetsListEffect
import com.saiferwp.swplanetviewerdemo.planets.viewmodel.PlanetsListUiState

@Composable
fun PlanetsListScreen(
    modifier: Modifier = Modifier,
    state: PlanetsListUiState,
    onRetry: () -> Unit = {},
    setEffect: (PlanetsListEffect) -> Unit = {}
) {
    Surface(modifier = modifier) {
        when (state) {
            is PlanetsListUiState.Loading -> {
                LoadingView()
            }

            is PlanetsListUiState.Error -> {
                ErrorView(message = state.errorMessage, onRetry = onRetry)
            }

            is PlanetsListUiState.Success -> {
                PlanetsList(
                    planetsList = state.planetsList,
                    modifier = Modifier.fillMaxSize(),
                    setEffect = setEffect
                )
            }
        }
    }
}

@Composable
fun PlanetsList(
    modifier: Modifier = Modifier,
    planetsList: List<PlanetsListItem>,
    setEffect: (PlanetsListEffect) -> Unit = {}
) {
    Surface(modifier = modifier) {
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = planetsList) { planet ->
                PlanetsListCard(
                    planetsListItem = planet,
                    onCardClicked = {
                        setEffect(PlanetsListEffect.NavigateToPlanetDetails(planet.id))
                    }
                )
            }
        }
    }
}

@Composable
fun PlanetsListCard(
    modifier: Modifier = Modifier,
    planetsListItem: PlanetsListItem,
    onCardClicked: () -> Unit = {}
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 2.dp,
        onClick = onCardClicked
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = planetsListItem.name,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = planetsListItem.climate,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = planetsListItem.population,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlanetsListScreenPreview() {
    SWPlanetViewerDemoTheme {
        PlanetsList(
            modifier = Modifier.fillMaxSize(),
            planetsList =
                listOf(
                    PlanetsListItem(
                        id = "1",
                        name = "Tatooine",
                        population = "200000",
                        climate = "Arid"
                    ),
                    PlanetsListItem(
                        id = "2",
                        name = "Alderaan",
                        population = "2000000000",
                        climate = "Temperate"
                    )
                )
        )
    }
}
