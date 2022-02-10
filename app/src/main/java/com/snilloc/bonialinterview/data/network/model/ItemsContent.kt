package com.snilloc.bonialinterview.data.network.model

data class ItemsContent(
    val id: String?,
    val publisherId: Long?,
    val publishedFrom: String?,
    val publishedUntil: String?,
    val linkOut: LinkOut?,
    val video: Video?,
    val thumbnail: Dimensions?,
    val type: String
)