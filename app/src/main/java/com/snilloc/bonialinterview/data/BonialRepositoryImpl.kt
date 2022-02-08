package com.snilloc.bonialinterview.data

import com.snilloc.bonialinterview.data.mappers.dtoToDomain
import com.snilloc.bonialinterview.domain.BonialRepository
import com.snilloc.bonialinterview.domain.model.BrochureData
import javax.inject.Inject

class BonialRepositoryImpl @Inject constructor(private val api: BonialApi) : BonialRepository {
    override suspend fun getBrochures(): List<BrochureData> {
        val networkResponse = api.getBrochures()

        val brochuresList = mutableListOf<BrochureData>()

        //Convert the response to the domain model
        val brochuresResponse = networkResponse.brochureResponse.contents
        brochuresResponse.forEach { contentsData ->
            if (contentsData.contentType.equals("brochure") || contentsData.contentType.equals("brochurePremium")) {
                //Loop through the contents
                contentsData.content?.forEach { content ->
                    //Filter for brochures closer than 5km
                    if (content.distance!! < 5.0F) {
                        val brochureData = dtoToDomain(contentsData, content)
                        brochuresList.add(brochureData)
                    }
                }
            }
        }
        return brochuresList
    }
}