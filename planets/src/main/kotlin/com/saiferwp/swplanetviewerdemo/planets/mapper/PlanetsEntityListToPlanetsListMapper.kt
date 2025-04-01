package com.saiferwp.swplanetviewerdemo.planets.mapper

import com.saiferwp.swplanetviewerdemo.core.model.PlanetsResponse.PlanetEntity
import com.saiferwp.swplanetviewerdemo.planets.model.Planet
import java.util.Locale

class PlanetsEntityListToPlanetsListMapper {

    fun transform(planetsEntityList: List<PlanetEntity>): List<Planet> {
        return planetsEntityList.map { it.toPlanet() }
    }

    private fun PlanetEntity.toPlanet() = Planet(
        name = name,
        climate = climate.replaceFirstChar { if (it. isLowerCase()) it. titlecase(Locale.getDefault()) else it. toString() },
        population = population
    )
}
