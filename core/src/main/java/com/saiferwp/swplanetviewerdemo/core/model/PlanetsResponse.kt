package com.saiferwp.swplanetviewerdemo.core.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlanetsResponse(
    val count: Int,
    val results: List<Planet>
) {
    @JsonClass(generateAdapter = true)
    data class Planet(
        val name: String,
        val climate: String,
        val population: String
    )
}
