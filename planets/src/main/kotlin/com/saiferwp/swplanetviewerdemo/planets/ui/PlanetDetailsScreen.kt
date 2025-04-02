package com.saiferwp.swplanetviewerdemo.planets.ui

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PlanetDetailsScreen(
    modifier: Modifier = Modifier,
    planetId: String?
) {
    Surface(modifier = modifier) {
        Text(text = planetId ?: "")
    }
}