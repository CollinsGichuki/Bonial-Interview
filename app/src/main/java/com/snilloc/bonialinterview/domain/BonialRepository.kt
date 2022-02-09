package com.snilloc.bonialinterview.domain

import com.snilloc.bonialinterview.data.model.BrochureResponse
import com.snilloc.bonialinterview.domain.model.BrochureData
import com.snilloc.bonialinterview.util.Resource

interface BonialRepository {
    suspend fun getBrochures(): Resource<List<BrochureData>>
}