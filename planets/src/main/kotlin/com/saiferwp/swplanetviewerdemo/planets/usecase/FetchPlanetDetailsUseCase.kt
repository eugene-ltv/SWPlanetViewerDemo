package com.saiferwp.swplanetviewerdemo.planets.usecase

import com.saiferwp.swplanetviewerdemo.core.FlowUseCase
import com.saiferwp.swplanetviewerdemo.core.api.Api
import com.saiferwp.swplanetviewerdemo.planets.mapper.PlanetEntityResponseToPlanetDetailsUiStateMapper
import com.saiferwp.swplanetviewerdemo.planets.viewmodel.PlanetDetailsUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class FetchPlanetDetailsUseCase @Inject constructor(
    coroutineContext: CoroutineContext,
    private val mapper: PlanetEntityResponseToPlanetDetailsUiStateMapper,
    private val api: Api
) : FlowUseCase<String, PlanetDetailsUiState>(coroutineContext) {

    override fun execute(parameters: String): Flow<PlanetDetailsUiState> = flow {
        val result = try {
            val response = api.getPlanetDetails(parameters)
            mapper.transform(response)
        } catch (ex: Exception) {
            mapper.transformException(ex)
        }

        emit(result)
    }
}
