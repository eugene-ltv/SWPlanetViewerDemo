package com.saiferwp.swplanetviewerdemo.planets.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saiferwp.swplanetviewerdemo.core.R
import com.saiferwp.swplanetviewerdemo.core.ui.theme.SWPlanetViewerDemoTheme
import com.saiferwp.swplanetviewerdemo.planets.model.Planet
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
    planetsList: List<Planet>,
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
                    planet = planet,
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
    planet: Planet,
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

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorView(message: String, onRetry: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = message,
                modifier = Modifier.padding(horizontal = 40.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onRetry) {
                Text(text = stringResource(R.string.retry))
            }
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
                    Planet(
                        id = "1",
                        name = "Tatooine",
                        population = "200000",
                        climate = "Arid"
                    ),
                    Planet(
                        id = "2",
                        name = "Alderaan",
                        population = "2000000000",
                        climate = "Temperate"
                    )
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    SWPlanetViewerDemoTheme {
        LoadingView()
    }
}


@Preview(showBackground = true)
@Composable
fun ErrorPreview() {
    SWPlanetViewerDemoTheme {
        ErrorView("Error message") {}
    }
}