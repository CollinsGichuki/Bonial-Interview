package com.snilloc.bonialinterview.data

import com.snilloc.bonialinterview.data.model.BrochureResponse
import retrofit2.http.GET

interface BonialApi {
    //Get the brochures
    @GET("stories-test/shelf.json")
    suspend fun getBrochures(): BrochureResponse
}