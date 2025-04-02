package com.saiferwp.swplanetviewerdemo.planets.mapper

import android.content.Context
import com.saiferwp.swplanetviewerdemo.core.R
import com.saiferwp.swplanetviewerdemo.core.model.PlanetsResponse
import com.saiferwp.swplanetviewerdemo.core.model.PlanetsResponse.PlanetEntity
import com.saiferwp.swplanetviewerdemo.planets.model.Planet
import com.saiferwp.swplanetviewerdemo.planets.viewmodel.PlanetsListUiState
import retrofit2.Response
import java.util.Locale

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

    private fun PlanetEntity.toPlanet() = Planet(
        id = url.split("/").let {
            it.getOrNull(it.lastIndex - 1) ?: ""
        },
        name = name,
        climate = climate.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
        population = population
    )
}
