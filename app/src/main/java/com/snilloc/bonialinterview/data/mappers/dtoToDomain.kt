package com.snilloc.bonialinterview.data.mappers

import com.snilloc.bonialinterview.data.model.EmbeddedData
import com.snilloc.bonialinterview.domain.model.BrochureData

fun dtoToDomain(
    contentsData: EmbeddedData.ContentsData,
    content: EmbeddedData.ContentsData.Content
): BrochureData {
    return BrochureData(
        id = content.id,
        contentType = contentsData.contentType,
        brochureImage = content.brochureImage,
        retailerName = content.retailer?.name,
        distance = content.distance
    )
}