package com.saiferwp.swplanetviewerdemo.planets.mapper

import android.content.Context
import com.saiferwp.swplanetviewerdemo.core.R
import com.saiferwp.swplanetviewerdemo.core.model.PlanetsResponse.PlanetEntity
import com.saiferwp.swplanetviewerdemo.core.utils.capitalizeLocalised
import com.saiferwp.swplanetviewerdemo.planets.model.PlanetDetails
import com.saiferwp.swplanetviewerdemo.planets.viewmodel.PlanetDetailsUiState
import retrofit2.Response

class PlanetEntityResponseToPlanetDetailsUiStateMapper(
    val context: Context
) {

    fun transform(response: Response<PlanetEntity>): PlanetDetailsUiState {
        val responseBody = response.body()
        val result = if (response.isSuccessful && responseBody != null) {
            val planets = responseBody.toPlanet()
            PlanetDetailsUiState.Success(planets)
        } else {
            val errorBody =
                response.errorBody()?.string() ?: context.getString(R.string.unknown_error)
            PlanetDetailsUiState.Error(
                context.getString(
                    R.string.unsuccessful_response,
                    response.code(),
                    errorBody
                )
            )
        }

        return result
    }

    fun transformException(exception: Exception): PlanetDetailsUiState {
        return PlanetDetailsUiState.Error(
            exception.message ?: context.getString(R.string.unknown_exception)
        )
    }

    private fun PlanetEntity.toPlanet() = PlanetDetails(
        name = name,
        climate = climate.capitalizeLocalised(),
        population = population,
        diameter = diameter,
        gravity = gravity.capitalizeLocalised(),
        terrain = terrain.capitalizeLocalised()
    )
}
