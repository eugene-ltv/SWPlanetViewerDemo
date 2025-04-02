package com.saiferwp.swplanetviewerdemo.planets.viewmodel

import androidx.lifecycle.viewModelScope
import com.saiferwp.swplanetviewerdemo.core.BaseViewModel
import com.saiferwp.swplanetviewerdemo.planets.usecase.FetchPlanetDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class PlanetDetailsViewModel @Inject constructor(
    private val fetchPlanetDetailsUseCase: FetchPlanetDetailsUseCase
) : BaseViewModel<PlanetDetailsUiState, PlanetDetailsEvent, PlanetDetailsEffect>() {

    override fun setInitialState() =
        PlanetDetailsUiState.Loading

    override fun handleEvents(event: PlanetDetailsEvent) {
        when (event) {
            is PlanetDetailsEvent.FetchById -> requestPlanetDetails(event.planetId)
        }
    }

    private fun requestPlanetDetails(planetId: String) {
        fetchPlanetDetailsUseCase.invoke(planetId)
            .onStart {
                setState {
                    PlanetDetailsUiState.Loading
                }
            }
            .onEach { uiState ->
                setState { uiState }
            }
            .launchIn(viewModelScope)
    }
}
