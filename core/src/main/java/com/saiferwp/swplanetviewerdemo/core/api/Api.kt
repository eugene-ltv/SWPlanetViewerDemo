package com.saiferwp.swplanetviewerdemo.core.api

import retrofit2.http.GET

interface Api {

    @GET("planets")
    suspend fun getPlanetsResponse(): Any
}
