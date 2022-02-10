package com.snilloc.bonialinterview.data.network.model

data class LinksData(
    val self: SelfData
) {
    data class SelfData(
        val href: String
    )
}