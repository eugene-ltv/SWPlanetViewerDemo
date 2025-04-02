package com.saiferwp.swplanetviewerdemo.planets.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saiferwp.swplanetviewerdemo.core.R
import com.saiferwp.swplanetviewerdemo.core.ui.compose.ErrorView
import com.saiferwp.swplanetviewerdemo.core.ui.compose.LoadingView
import com.saiferwp.swplanetviewerdemo.core.ui.theme.SWPlanetViewerDemoTheme
import com.saiferwp.swplanetviewerdemo.planets.model.PlanetDetails
import com.saiferwp.swplanetviewerdemo.planets.viewmodel.PlanetDetailsUiState

@Composable
fun PlanetDetailsScreen(
    modifier: Modifier = Modifier,
    state: PlanetDetailsUiState,
    onRetry: () -> Unit = {}
) {
    Surface(modifier = modifier) {
        when (state) {
            PlanetDetailsUiState.Loading -> {
                LoadingView()
            }

            is PlanetDetailsUiState.Success -> {
                PlanetDetails(
                    Modifier.padding(16.dp),
                    planetDetails = state.data
                )
            }

            is PlanetDetailsUiState.Error -> {
                ErrorView(
                    message = state.errorMessage,
                    onRetry = onRetry
                )
            }
        }
    }
}

@Composable
fun PlanetDetails(
    modifier: Modifier = Modifier,
    planetDetails: PlanetDetails
) {
    Surface(modifier = modifier) {
        // optional - move to presentation layer
        val items = listOf(
            stringResource(R.string.climate) to planetDetails.climate,
            stringResource(R.string.population) to planetDetails.population,
            stringResource(R.string.diameter) to planetDetails.diameter,
            stringResource(R.string.gravity) to planetDetails.gravity,
            stringResource(R.string.terrain) to planetDetails.terrain
        )

        LazyColumn {
            item {
                Column {
                    Text(
                        text = planetDetails.name,
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(Modifier.size(16.dp))
                }
            }

            items(items) { item ->
                Row {
                    Text(
                        text = item.first,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = item.second,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.weight(2f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlanetDetailsScreenPreview() {
    SWPlanetViewerDemoTheme {
        PlanetDetailsScreen(
            modifier = Modifier.fillMaxSize(),
            state = PlanetDetailsUiState.Success(
                PlanetDetails(
                    name = "Tatooine",
                    population = "200000",
                    climate = "Arid",
                    terrain = "Dessert",
                    diameter = "10465",
                    gravity = "1 standard",
                )
            )
        )
    }
}
