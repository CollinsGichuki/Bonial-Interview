package com.snilloc.bonialinterview.data

import com.snilloc.bonialinterview.data.model.BrochureResponse
import retrofit2.http.GET

interface BonialApi {
    //Get the brochures
    @GET(".")//Since there is no endpoint
    fun getBrochures(): BrochureResponse
}