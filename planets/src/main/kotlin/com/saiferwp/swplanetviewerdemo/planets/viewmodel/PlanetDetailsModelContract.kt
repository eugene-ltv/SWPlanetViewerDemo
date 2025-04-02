package com.saiferwp.swplanetviewerdemo.planets.viewmodel

import com.saiferwp.swplanetviewerdemo.core.ViewEvent
import com.saiferwp.swplanetviewerdemo.core.ViewSideEffect
import com.saiferwp.swplanetviewerdemo.core.ViewState
import com.saiferwp.swplanetviewerdemo.planets.model.PlanetDetails

sealed interface PlanetDetailsUiState : ViewState {
    data object Loading : PlanetDetailsUiState
    data class Error(
        val errorMessage: String
    ) : PlanetDetailsUiState

    data class Success(
        val data: PlanetDetails
    ) : PlanetDetailsUiState
}

sealed interface PlanetDetailsEvent : ViewEvent {
    data class FetchById(
        val planetId: String
    ) : PlanetDetailsEvent
}
object PlanetDetailsEffect : ViewSideEffect

