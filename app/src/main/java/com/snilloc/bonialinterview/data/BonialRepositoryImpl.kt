package com.snilloc.bonialinterview.data

import android.util.Log
import androidx.room.withTransaction
import com.snilloc.bonialinterview.data.cache.BrochureDatabase
import com.snilloc.bonialinterview.data.cache.model.BrochureEntity
import com.snilloc.bonialinterview.data.mappers.dtoToEntity
import com.snilloc.bonialinterview.data.network.BonialApi
import com.snilloc.bonialinterview.util.Resource
import javax.inject.Inject

class BonialRepositoryImpl @Inject constructor(
    private val api: BonialApi,
    private val db: BrochureDatabase
) : BonialRepository {
    override suspend fun getBrochures(): Resource<List<BrochureEntity>> {
        return try {

            //Make the network request and convert the data to entity type
            val networkBrochures = getBrochuresFromNetwork()

            //Update the cached data
            db.withTransaction {
                db.brochureDao().deleteBrochures()
                db.brochureDao().insertBrochures(networkBrochures)
            }

            Resource.success(networkBrochures)

        } catch (e: Exception) {
            Resource.error("There was a problem getting the data: $e")
        }
    }

    override suspend fun getFilteredBrochures(): Resource<List<BrochureEntity>> {
        return try {
            val filteredBrochures = db.brochureDao().getFilteredBrochures()

            Resource.success(filteredBrochures)
        } catch (e: Exception) {
            Resource.error("There was a problem getting the filtered data: $e")
        }
    }

    private suspend fun getBrochuresFromNetwork(): List<BrochureEntity> {
        val networkBrochures = mutableListOf<BrochureEntity>()
        //Make the network call
        val networkResponse = api.getBrochures()

        //Convert the response to the domain model
        val brochuresResponse = networkResponse.brochureResponse.contents

        brochuresResponse.forEach { contentsData ->

            if (contentsData.contentType == "brochurePremium" || contentsData.contentType == "brochure") {

                contentsData.content.content?.forEach { content ->
                    val brochureData = dtoToEntity(contentsData, content)

                    networkBrochures.add(brochureData)
                }
            }
        }

        return networkBrochures
    }
}