package com.snilloc.bonialinterview.domain

import com.snilloc.bonialinterview.domain.model.BrochureData

interface BonialRepository {
    suspend fun getBrochures(): List<BrochureData>
}