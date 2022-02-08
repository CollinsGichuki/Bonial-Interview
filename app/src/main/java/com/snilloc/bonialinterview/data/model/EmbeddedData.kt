package com.snilloc.bonialinterview.data.model

import com.google.gson.annotations.SerializedName

data class EmbeddedData(val contents: List<ContentsData>) {
    data class ContentsData(
        val placement: String?,
        val adFormat: String?,
        val contentFormatSource: String?,
        val contentType: String?,
        val externalTracking: ExternalTrackingData?,
        val content: List<Content>?
    ) {
        data class ExternalTrackingData(
            val impression: List<String>?,
            val click: List<String>?
        )

        data class Content(
            val id: Int?,
            @SerializedName("campaign_item_id")
            val campaignItemId: String?,
            val title: String?,
            val link: String?,
            @SerializedName("imgURL")
            val imageURL: String?,
            val teaserText: String?,
            val validFrom: String?,
            val validUntil: String?,
            val publishedFrom: String?,
            val publishedUntil: String?,
            val type: String?,
            val pageCount: Int?,
            val publisher: PublisherData?,
            val retailer: RetailerData?,
            val brochureImage: String?,
            val badges: List<String>?,
            val isEcommerce: Boolean?,
            val distance: Float?,
            val hideValidityDate: Boolean?
        ) {
            data class PublisherData(
                val id: Int,
                val name: String,
                val type: String
            )

            data class RetailerData(
                val id: Int,
                val name: String
            )
        }
    }
}