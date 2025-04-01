package com.saiferwp.swplanetviewerdemo.planets.viewmodel

import androidx.lifecycle.ViewModel
import com.saiferwp.swplanetviewerdemo.core.api.Api
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlanetsListViewModel @Inject constructor(
    private val api: Api
): ViewModel() {

    suspend fun doStuff() {
        println(api.getPlanetsResponse())
    }
}
