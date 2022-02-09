package com.snilloc.bonialinterview.data.mappers

import com.snilloc.bonialinterview.data.model.Content
import com.snilloc.bonialinterview.data.model.ContentsData
import com.snilloc.bonialinterview.data.model.EmbeddedData
import com.snilloc.bonialinterview.domain.model.BrochureData

fun dtoToDomain(
    contentsData: ContentsData,
    content: Content
): BrochureData {
    return BrochureData(
        id = content.id,
        contentType = contentsData.contentType,
        brochureImage = content.brochureImage,
        retailerName = content.retailer?.name,
        distance = content.distance
    )
}