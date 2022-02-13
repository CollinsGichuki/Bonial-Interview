package com.snilloc.bonialinterview.data

import com.snilloc.bonialinterview.data.cache.model.BrochureEntity
import com.snilloc.bonialinterview.util.Resource

interface BonialRepository {
    suspend fun getBrochures(): Resource<List<BrochureEntity>>

    suspend fun getFilteredBrochures(): Resource<List<BrochureEntity>>
}