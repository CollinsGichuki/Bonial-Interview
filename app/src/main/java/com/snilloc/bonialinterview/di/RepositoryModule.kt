package com.snilloc.bonialinterview.di

import com.snilloc.bonialinterview.data.BonialRepositoryImpl
import com.snilloc.bonialinterview.data.cache.BrochureDatabase
import com.snilloc.bonialinterview.data.network.BonialApi
import com.snilloc.bonialinterview.data.BonialRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideBrochureRepository(api: BonialApi, db: BrochureDatabase): BonialRepository {
        return BonialRepositoryImpl(api, db)
    }
}