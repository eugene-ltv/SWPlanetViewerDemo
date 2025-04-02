package com.saiferwp.swplanetviewerdemo.planets.viewmodel

import com.saiferwp.swplanetviewerdemo.core.ViewEvent
import com.saiferwp.swplanetviewerdemo.core.ViewState
import com.saiferwp.swplanetviewerdemo.planets.model.Planet

sealed interface PlanetsListUiState: ViewState {
    data object Loading : PlanetsListUiState
    data class Error(
        val errorMessage: String
    ) : PlanetsListUiState

    data class Success(
        val planetsList: List<Planet>
    ) : PlanetsListUiState
}


sealed interface PlanetsListEvent : ViewEvent {
    data object FetchList : PlanetsListEvent
    data object ReFetchList : PlanetsListEvent
}
