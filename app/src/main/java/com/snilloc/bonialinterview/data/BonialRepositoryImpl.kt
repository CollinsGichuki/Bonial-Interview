package com.snilloc.bonialinterview.data

import com.snilloc.bonialinterview.data.mappers.dtoToDomain
import com.snilloc.bonialinterview.domain.BonialRepository
import com.snilloc.bonialinterview.domain.model.BrochureData
import com.snilloc.bonialinterview.util.Resource
import javax.inject.Inject

class BonialRepositoryImpl @Inject constructor(private val api: BonialApi) : BonialRepository {
    override suspend fun getBrochures(): Resource<List<BrochureData>> {
        return try {
            val networkResponse = api.getBrochures()

            val brochuresList = mutableListOf<BrochureData>()

            var brochureDataSize = 0

            //Convert the response to the domain model
            val brochuresResponse = networkResponse.brochureResponse.contents

            brochuresResponse.forEach { contentsData ->

                if (contentsData.contentType == "brochurePremium" || contentsData.contentType == "brochure") {

                    contentsData.content.content?.forEach { content ->
                        if (content.distance!! < 5.0F) {
                            val brochureData = dtoToDomain(contentsData, content)

                            brochuresList.add(brochureData)
                            brochureDataSize++
                        }
                    }
                }
            }
            Resource.success(brochuresList)
        } catch (e: Exception) {
            Resource.error("There was a problem getting the data: $e")
        }
    }
}