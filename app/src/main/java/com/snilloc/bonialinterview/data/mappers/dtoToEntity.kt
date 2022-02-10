package com.snilloc.bonialinterview.data.mappers

import com.snilloc.bonialinterview.data.cache.model.BrochureEntity
import com.snilloc.bonialinterview.data.network.model.Content
import com.snilloc.bonialinterview.data.network.model.ContentsData

fun dtoToEntity(
    contentsData: ContentsData,
    content: Content
): BrochureEntity {
    return BrochureEntity(
        id = content.id,
        contentType = contentsData.contentType,
        brochureImage = content.brochureImage,
        retailerName = content.retailer?.name,
        distance = content.distance
    )
}