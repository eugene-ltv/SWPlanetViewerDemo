package com.saiferwp.swplanetviewerdemo.core.api

import com.saiferwp.swplanetviewerdemo.core.model.PlanetsResponse
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("planets")
    suspend fun getPlanetsResponse(): Response<PlanetsResponse>
}
