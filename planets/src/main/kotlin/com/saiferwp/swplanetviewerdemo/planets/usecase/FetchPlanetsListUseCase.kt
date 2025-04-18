package com.saiferwp.swplanetviewerdemo.planets.usecase

import com.saiferwp.swplanetviewerdemo.core.FlowUseCase
import com.saiferwp.swplanetviewerdemo.core.api.Api
import com.saiferwp.swplanetviewerdemo.planets.mapper.PlanetsResponseToPlanetsListUiStateMapper
import com.saiferwp.swplanetviewerdemo.planets.viewmodel.PlanetsListUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class FetchPlanetsListUseCase @Inject constructor(
    coroutineContext: CoroutineContext,
    private val mapper: PlanetsResponseToPlanetsListUiStateMapper,
    private val api: Api
) : FlowUseCase<Unit, PlanetsListUiState>(coroutineContext) {

    override fun execute(parameters: Unit): Flow<PlanetsListUiState> = flow {
        val result = try {
            val response = api.getPlanets()
            mapper.transform(response)
        } catch (ex: Exception) {
            mapper.transformException(ex)
        }

        emit(result)
    }
}
