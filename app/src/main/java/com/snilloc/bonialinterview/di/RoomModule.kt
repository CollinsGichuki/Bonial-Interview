package com.snilloc.bonialinterview.di

import android.content.Context
import androidx.room.Room
import com.snilloc.bonialinterview.data.cache.BrochureDao
import com.snilloc.bonialinterview.data.cache.BrochureDatabase
import com.snilloc.bonialinterview.util.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideBrochureDatabase(@ApplicationContext context: Context): BrochureDatabase {
        return Room.databaseBuilder(
            context,
            BrochureDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideBrochureDao(brochureDatabase: BrochureDatabase): BrochureDao {
        return brochureDatabase.brochureDao()
    }
}