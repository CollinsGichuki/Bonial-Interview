package com.snilloc.bonialinterview.data.model

data class LinksData(
    val self: SelfData
) {
    data class SelfData(
        val href: String
    )
}