package com.saiferwp.swplanetviewerdemo.core.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlanetsResponse(
    val count: Int,
    val results: List<PlanetEntity>
) {
    @JsonClass(generateAdapter = true)
    data class PlanetEntity(
        val name: String,
        val climate: String,
        val population: String,
        val diameter: String,
        val gravity: String,
        val terrain: String,
        val url: String
    )
}
