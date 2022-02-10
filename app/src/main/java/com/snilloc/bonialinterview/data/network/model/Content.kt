package com.snilloc.bonialinterview.data.network.model

import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("content")
    var innerContent: InnerContent?= null,
    var externalTracking: ExternalTrackingData? = null,
    var id: Int? = null,
    @SerializedName("campaign_item_id")
    var campaignItemId: String? = null,
    var title: String? = null,
    var link: String? = null,
    @SerializedName("imgURL")
    var imageURL: String? = null,
    @SerializedName("imageUrl")
    var imageUrl2: String? = null,
    var teaserText: String? = null,
    var validFrom: String? = null,
    var validUntil: String? = null,
    var publishedFrom: String? = null,
    var publishedUntil: String? = null,
    var type: String? = null,
    var pageCount: Int? = null,
    var publisher: PublisherData? = null,
    var retailer: RetailerData? = null,
    var brochureImage: String? = null,
    val badges: List<String>? = null,
    var isEcommerce: Boolean? = null,
    var distance: Float? = null,
    var hideValidityDate: Boolean? = null,
    var publisherId: String? = null,
    var publisherImage: String? = null,
    val items: List<Item>? = null
)