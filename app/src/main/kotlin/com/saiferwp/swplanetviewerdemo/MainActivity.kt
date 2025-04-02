package com.saiferwp.swplanetviewerdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.saiferwp.swplanetviewerdemo.core.ui.theme.SWPlanetViewerDemoTheme
import com.saiferwp.swplanetviewerdemo.planets.ui.PlanetsListScreen
import com.saiferwp.swplanetviewerdemo.planets.viewmodel.PlanetsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SWPlanetViewerDemoTheme {
                val viewModel = hiltViewModel<PlanetsListViewModel>()
                val planetsListUiState by
                viewModel.planetsListUiStateFlow.collectAsStateWithLifecycle()

                LaunchedEffect(Unit) {
                    viewModel.requestPlanetsList()
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PlanetsListScreen(
                        modifier = Modifier.padding(innerPadding),
                        state = planetsListUiState
                    )
                }
            }
        }
    }
}
