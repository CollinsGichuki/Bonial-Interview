package com.snilloc.bonialinterview.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.snilloc.bonialinterview.data.cache.model.BrochureEntity

@Dao
interface BrochureDao {
    @Query("SELECT * FROM brochure")
    fun getALlBrochures(): List<BrochureEntity>

    @Query("SELECT * FROM brochure WHERE distance<5")
    fun getFilteredBrochures(): List<BrochureEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBrochures(brochures: List<BrochureEntity>)

    @Query("DELETE FROM brochure")
    suspend fun deleteBrochures()
}