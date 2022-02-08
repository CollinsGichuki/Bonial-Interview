package com.snilloc.bonialinterview.domain

import com.snilloc.bonialinterview.data.model.BrochureResponse

interface BonialRepository {
    suspend fun getBrochures() : BrochureResponse
}