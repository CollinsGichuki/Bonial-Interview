package com.snilloc.bonialinterview.data.network.model

data class ContentsData(
    val placement: String?,
    val adFormat: String?,
    val contentFormatSource: String?,
    val contentType: String?,
    val externalTracking: ExternalTrackingData?,
    val content: ContentList,
)