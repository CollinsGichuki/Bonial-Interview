package com.snilloc.bonialinterview.data.network

import com.snilloc.bonialinterview.data.network.model.BrochureResponse
import retrofit2.http.GET

interface BonialApi {
    //Get the brochures
    @GET("stories-test/shelf.json")
    suspend fun getBrochures(): BrochureResponse
}