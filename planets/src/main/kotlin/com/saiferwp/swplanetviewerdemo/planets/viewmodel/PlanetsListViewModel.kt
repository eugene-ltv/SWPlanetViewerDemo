package com.saiferwp.swplanetviewerdemo.planets.viewmodel

import androidx.lifecycle.viewModelScope
import com.saiferwp.swplanetviewerdemo.core.BaseViewModel
import com.saiferwp.swplanetviewerdemo.planets.usecase.FetchPlanetsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class PlanetsListViewModel @Inject constructor(
    private val fetchPlanetsListUseCase: FetchPlanetsListUseCase
) : BaseViewModel<PlanetsListUiState, PlanetsListEvent>() {

    override fun setInitialState() =
        PlanetsListUiState.Loading

    override fun onInit() {
        handleEvents(PlanetsListEvent.FetchList)
    }

    override fun handleEvents(event: PlanetsListEvent) {
        when (event) {
            is PlanetsListEvent.FetchList -> requestPlanetsList()
            is PlanetsListEvent.ReFetchList -> requestPlanetsList()
        }
    }

    private fun requestPlanetsList() {
        fetchPlanetsListUseCase.invoke(Unit)
            .onStart {
                setState {
                    PlanetsListUiState.Loading
                }
            }
            .onEach { planetsListUiState ->
                setState { planetsListUiState }
            }
            .launchIn(viewModelScope)
    }

}
