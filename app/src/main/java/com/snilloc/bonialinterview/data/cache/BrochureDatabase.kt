package com.snilloc.bonialinterview.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.snilloc.bonialinterview.data.cache.model.BrochureEntity

@Database(entities = [BrochureEntity::class], version = 1)
abstract class BrochureDatabase : RoomDatabase() {

    abstract fun brochureDao(): BrochureDao
}