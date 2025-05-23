package com.saiferwp.swplanetviewerdemo.planets.mapper

import android.content.Context
import com.saiferwp.swplanetviewerdemo.core.R
import com.saiferwp.swplanetviewerdemo.core.model.PlanetsResponse
import com.saiferwp.swplanetviewerdemo.core.model.PlanetsResponse.PlanetEntity
import com.saiferwp.swplanetviewerdemo.core.utils.capitalizeLocalised
import com.saiferwp.swplanetviewerdemo.core.utils.getLastPathElementOrEmpty
import com.saiferwp.swplanetviewerdemo.planets.model.PlanetsListItem
import com.saiferwp.swplanetviewerdemo.planets.viewmodel.PlanetsListUiState
import retrofit2.Response

class PlanetsResponseToPlanetsListUiStateMapper(
    val context: Context
) {

    fun transform(response: Response<PlanetsResponse>): PlanetsListUiState {
        val responseBody = response.body()
        val result = if (response.isSuccessful && responseBody != null) {
            val planets = responseBody.results.map { it.toPlanet() }
            PlanetsListUiState.Success(planets)
        } else {
            val errorBody =
                response.errorBody()?.string() ?: context.getString(R.string.unknown_error)
            PlanetsListUiState.Error(
                context.getString(
                    R.string.unsuccessful_response,
                    response.code(),
                    errorBody
                )
            )
        }

        return result
    }

    fun transformException(exception: Exception): PlanetsListUiState {
        return PlanetsListUiState.Error(
            exception.message ?: context.getString(R.string.unknown_exception)
        )
    }

    private fun PlanetEntity.toPlanet() = PlanetsListItem(
        id = url.getLastPathElementOrEmpty(),
        name = name,
        climate = climate.capitalizeLocalised(),
        population = population
    )
}
