package com.snilloc.bonialinterview.data

import com.google.common.truth.Truth.assertThat
import com.snilloc.bonialinterview.data.mappers.dtoToDomain
import com.snilloc.bonialinterview.data.model.EmbeddedData
import com.snilloc.bonialinterview.domain.BonialRepository
import com.snilloc.bonialinterview.domain.model.BrochureData
import org.junit.Test

class FakeBonialRepositoryImpl : BonialRepository {
    private val brochureDataList = listOf<BrochureData>(
        BrochureData(
            id = 1,
            contentType = "brochurePremium",
            brochureImage = "https://content-media.bonial.biz/d753a0a0-7fc7-4b92-b34b-5146d1b692ad/preview.jpg",
            retailerName = "Retailer A",
            distance = 1.0F
        ),
        BrochureData(
            id = 2,
            contentType = "brochure",
            brochureImage = "https://content-media.bonial.biz/d753a0a0-7fc7-4b92-b34b-5146d1b692ad/preview.jpg",
            retailerName = "Retailer B",
            distance = 1.0F
        ),
        BrochureData(
            id = 3,
            contentType = "brochurePremium",
            brochureImage = "https://content-media.bonial.biz/d753a0a0-7fc7-4b92-b34b-5146d1b692ad/preview.jpg",
            retailerName = "Retailer C",
            distance = 1.0F
        )
    )

    override suspend fun getBrochures(): List<BrochureData> {
        return brochureDataList
    }

    private val contentData = listOf<EmbeddedData.ContentsData>(
        EmbeddedData.ContentsData(
            placement = null,
            adFormat = null,
            contentFormatSource = null,
            contentType = "brochure",
            externalTracking = null,
            content = listOf(
                EmbeddedData.ContentsData.Content(
                    id = 1,
                    title = null,
                    campaignItemId = null,
                    link = null,
                    imageURL = null,
                    teaserText = null,
                    validFrom = null,
                    validUntil = null,
                    publishedFrom = null,
                    publishedUntil = null,
                    type = null,
                    pageCount = 1,
                    publisher = null,
                    retailer = EmbeddedData.ContentsData.Content.RetailerData(
                        id = 11,
                        name = "Retailer A"
                    ),
                    brochureImage = "someImage.jpg",
                    badges = null,
                    isEcommerce = false,
                    distance = 5.0F,
                    hideValidityDate = true
                )
            )
        ),
        EmbeddedData.ContentsData(
            placement = null,
            adFormat = null,
            contentFormatSource = null,
            contentType = "brochurePremium",
            externalTracking = null,
            content = listOf(
                EmbeddedData.ContentsData.Content(
                    id = 3,
                    title = null,
                    campaignItemId = null,
                    link = null,
                    imageURL = null,
                    teaserText = null,
                    validFrom = null,
                    validUntil = null,
                    publishedFrom = null,
                    publishedUntil = null,
                    type = null,
                    pageCount = 1,
                    publisher = null,
                    retailer = EmbeddedData.ContentsData.Content.RetailerData(
                        id = 11,
                        name = "Retailer B"
                    ),
                    brochureImage = "someOtherImage.jpg",
                    badges = null,
                    isEcommerce = false,
                    distance = 3.0F,
                    hideValidityDate = true
                )
            )
        ),
        EmbeddedData.ContentsData(
            placement = null,
            adFormat = null,
            contentFormatSource = null,
            contentType = "random",
            externalTracking = null,
            content = listOf(
                EmbeddedData.ContentsData.Content(
                    id = 2,
                    title = null,
                    campaignItemId = null,
                    link = null,
                    imageURL = null,
                    teaserText = null,
                    validFrom = null,
                    validUntil = null,
                    publishedFrom = null,
                    publishedUntil = null,
                    type = null,
                    pageCount = 1,
                    publisher = null,
                    retailer = EmbeddedData.ContentsData.Content.RetailerData(
                        id = 11,
                        name = "Retailer B"
                    ),
                    brochureImage = "someOtherImage.jpg",
                    badges = null,
                    isEcommerce = false,
                    distance = 4.9F,
                    hideValidityDate = true
                )
            )
        ),
    )

    @Test
    fun `verify that BrochureData item is stored in the brochureList after mapping to domain`() {
        val brochureList = mutableListOf<BrochureData>()

        contentData.forEach { it ->
            it.content?.forEach { content ->
                val brochureData = dtoToDomain(it, content)
                brochureList.add(brochureData)
            }
        }

        assertThat(brochureList).contains(
            BrochureData(
                id = 2,
                contentType = "random",
                brochureImage = "someOtherImage.jpg",
                retailerName = "Retailer B",
                distance = 4.9F
            )
        )
    }

    /**
     * There are 3 values in the list
     * 2 are of type brochure and brochurePremium respectively
     * the 3rd one is of type random
     * brochureList should only have the 2 items of the right type(brochure/brochurePremium)
     */
    @Test
    fun `verify only two values are stored in contentsList after filtering using contentType`() {
        val brochuresList = mutableListOf<EmbeddedData.ContentsData>()

        contentData.forEach { it ->
            if (it.contentType.equals("brochure") || it.contentType.equals("brochurePremium")) {
                //Loop through the contents
                brochuresList.add(it)
            }
        }
        assertThat(brochuresList.size).isEqualTo(2)
    }

    /**
     * There are 3 values in the list
     * 2 have a distance of 4.9F and 3.0 F respectively
     * the 3rd has a distance of 5.0F
     * contentList should only have the 2 items of the right distance range(<5)
     */
    @Test
    fun `verify only two values are stored in brochureList after filtering using distance`() {
        val contentList = mutableListOf<EmbeddedData.ContentsData.Content>()

        contentData.forEach { contentsData ->
            contentsData.content?.forEach { content ->
                if (content.distance!! < 5.0F) {
                    contentList.add(content)
                }
            }
        }

        assertThat(contentList.size).isEqualTo(2)
    }
}