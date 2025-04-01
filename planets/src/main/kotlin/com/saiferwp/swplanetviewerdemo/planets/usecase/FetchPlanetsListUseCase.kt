package com.saiferwp.swplanetviewerdemo.planets.usecase

import com.saiferwp.swplanetviewerdemo.core.FlowUseCase
import com.saiferwp.swplanetviewerdemo.core.api.Api
import com.saiferwp.swplanetviewerdemo.core.model.PlanetsResponse
import com.saiferwp.swplanetviewerdemo.planets.mapper.PlanetsEntityListToPlanetsListMapper
import com.saiferwp.swplanetviewerdemo.planets.model.Planet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class FetchPlanetsListUseCase @Inject constructor(
    coroutineContext: CoroutineContext,
    private val planetsEntityListToPlanetsListMapper: PlanetsEntityListToPlanetsListMapper,
    private val api: Api
) : FlowUseCase<Unit, PlanetsListResult>(coroutineContext) {

    override fun execute(parameters: Unit): Flow<PlanetsListResult> = flow {
        val response: Response<PlanetsResponse>
        try {
            response = api.getPlanetsResponse()
        } catch (ex: Exception) {
            emit(PlanetsListResult.Error(ex.message ?: "Unknown exception"))
            return@flow
        }
        val responseBody = response.body()
        val result = if (response.isSuccessful && responseBody != null) {
            val planets = planetsEntityListToPlanetsListMapper.transform(responseBody.results)
            PlanetsListResult.Success(planets)
        } else {
            val errorBody = response.errorBody()?.string() ?: "Unknown error"
            PlanetsListResult.Error("Error code: ${response.code()}, $errorBody")
        }

        emit(result)
    }
}

sealed class PlanetsListResult {
    internal data class Success(
        val data: List<Planet>
    ) : PlanetsListResult()

    internal data class Error(val message: String) : PlanetsListResult()
}
