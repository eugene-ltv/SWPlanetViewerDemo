package com.saiferwp.swplanetviewerdemo.planets.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saiferwp.swplanetviewerdemo.planets.model.Planet
import com.saiferwp.swplanetviewerdemo.planets.usecase.FetchPlanetsListUseCase
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

    private val _planetsListUiStateFlow =
        MutableStateFlow<PlanetsListUiState>(PlanetsListUiState.Loading)
    val planetsListUiStateFlow = _planetsListUiStateFlow
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            PlanetsListUiState.Loading
        )

    fun requestPlanetsList() {
        fetchPlanetsListUseCase.invoke(Unit)
            .onEach { planetsListUiState ->
                _planetsListUiStateFlow.update { planetsListUiState }
            }
            .launchIn(viewModelScope)
    }
}

sealed interface PlanetsListUiState {
    data object Loading : PlanetsListUiState
    data class Error(
        val errorMessage: String
    ) : PlanetsListUiState

    data class Success(
        val planetsList: List<Planet>
    ) : PlanetsListUiState
}