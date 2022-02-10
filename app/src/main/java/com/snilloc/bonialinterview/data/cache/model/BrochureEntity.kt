package com.snilloc.bonialinterview.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "brochure")
data class BrochureEntity(
    @PrimaryKey
    val id: Int?,
    val contentType: String?,
    val brochureImage: String?,
    val retailerName: String?,
    val distance: Float?
)