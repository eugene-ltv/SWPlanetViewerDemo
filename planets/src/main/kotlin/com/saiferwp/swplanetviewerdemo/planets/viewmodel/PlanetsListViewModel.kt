package com.saiferwp.swplanetviewerdemo.planets.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saiferwp.swplanetviewerdemo.planets.model.Planet
import com.saiferwp.swplanetviewerdemo.planets.usecase.FetchPlanetsListUseCase
import com.saiferwp.swplanetviewerdemo.planets.usecase.PlanetsListResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PlanetsListViewModel @Inject constructor(
    private val fetchPlanetsListUseCase: FetchPlanetsListUseCase
) : ViewModel() {

    private val _planetsListStateFlow =
        MutableStateFlow<List<Planet>>(emptyList())
    val planetsListStateFlow = _planetsListStateFlow
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            emptyList()
        )

    fun requestPlanetsList() {
        fetchPlanetsListUseCase.invoke(Unit)
            .onEach { planetsListResult ->
                if (planetsListResult is PlanetsListResult.Success) {
                    _planetsListStateFlow.update {
                        planetsListResult.data
                    }
                } else {
                    // do nothing for now
                }
            }
            .launchIn(viewModelScope)
    }
}
