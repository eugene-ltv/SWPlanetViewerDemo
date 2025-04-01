package com.saiferwp.swplanetviewerdemo.planets.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saiferwp.swplanetviewerdemo.core.api.Api
import com.saiferwp.swplanetviewerdemo.core.model.PlanetsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetsListViewModel @Inject constructor(
    private val api: Api
) : ViewModel() {

    private val _planetsListStateFlow = MutableStateFlow<List<PlanetsResponse.Planet>>(emptyList())
    val planetsListStateFlow = _planetsListStateFlow
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            emptyList()
        )

    fun requestPlanetsList() {
        viewModelScope.launch {
            val response = api.getPlanetsResponse()
            if (response.isSuccessful) {
                response.body()?.let { planets->
                    _planetsListStateFlow.update {
                        planets.results
                    }
                }
            } else {
                // do nothing for now
            }
        }
    }
}
