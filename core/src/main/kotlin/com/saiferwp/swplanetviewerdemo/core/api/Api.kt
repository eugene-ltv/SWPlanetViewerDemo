package com.saiferwp.swplanetviewerdemo.core.api

import com.saiferwp.swplanetviewerdemo.core.model.PlanetsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("planets")
    suspend fun getPlanets(): Response<PlanetsResponse>

    @GET("planets/{id}")
    suspend fun getPlanetDetails(
        @Path ("id") id: String
    ): Response<PlanetsResponse.PlanetEntity>
}
