package com.saiferwp.swplanetviewerdemo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.saiferwp.swplanetviewerdemo.core.ObserveAsEvents
import com.saiferwp.swplanetviewerdemo.planets.ui.PlanetDetailsScreen
import com.saiferwp.swplanetviewerdemo.planets.ui.PlanetsListScreen
import com.saiferwp.swplanetviewerdemo.planets.viewmodel.PlanetsListEffect
import com.saiferwp.swplanetviewerdemo.planets.viewmodel.PlanetsListEvent
import com.saiferwp.swplanetviewerdemo.planets.viewmodel.PlanetsListViewModel

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = PlanetsList.route,
        modifier = modifier
    ) {
        composable(route = PlanetsList.route) {
            val viewModel = hiltViewModel<PlanetsListViewModel>()
            val planetsListUiState by
            viewModel.viewState.collectAsStateWithLifecycle()

            ObserveAsEvents(viewModel.effect) { effect ->
                when (effect) {
                    is PlanetsListEffect.NavigateToPlanetDetails -> {
                        navController.navigate(
                            "${PlanetDetails.route}/${effect.planetId}"
                        )
                    }
                }
            }

            PlanetsListScreen(
                state = planetsListUiState,
                onRetry = {
                    viewModel.sendEvent(PlanetsListEvent.ReFetchList)
                },
                setEffect = viewModel::setEffect
            )
        }
        composable(
            route = PlanetDetails.routeWithArgs,
            arguments = PlanetDetails.arguments
        ) { navBackStackEntry ->
            val planetId =
                navBackStackEntry.arguments?.getString(PlanetDetails.accountTypeArg)
            PlanetDetailsScreen(
                planetId = planetId
            )
        }
    }
}

sealed interface MainDestination {
    val route: String
}

data object PlanetsList : MainDestination {
    override val route = "planets_list"
}

data object PlanetDetails : MainDestination {
    override val route = "planet_details"
    const val accountTypeArg = "account_type"
    val routeWithArgs = "$route/{$accountTypeArg}"
    val arguments = listOf(
        navArgument(accountTypeArg) { type = NavType.StringType }
    )
}