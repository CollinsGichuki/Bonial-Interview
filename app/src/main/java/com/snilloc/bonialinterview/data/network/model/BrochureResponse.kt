package com.snilloc.bonialinterview.data.network.model

import com.google.gson.annotations.SerializedName

data class BrochureResponse(
    @SerializedName("_embedded")
    val brochureResponse: EmbeddedData,
    @SerializedName("_links")
    val links: LinksData,
    val page: PageData
)