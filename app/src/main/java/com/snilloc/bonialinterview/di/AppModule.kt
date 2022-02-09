package com.snilloc.bonialinterview.di

import com.google.gson.GsonBuilder
import com.snilloc.bonialinterview.data.BonialApi
import com.snilloc.bonialinterview.data.BonialRepositoryImpl
import com.snilloc.bonialinterview.data.model.ContentList
import com.snilloc.bonialinterview.domain.BonialRepository
import com.snilloc.bonialinterview.util.Constants
import com.snilloc.bonialinterview.util.ContentAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBonialApi(): BonialApi {
        //Log the network calls
        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        //add interceptor
        val client = OkHttpClient.Builder().addInterceptor(logger).build()

        return Retrofit.Builder()
            .baseUrl(Constants.URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().registerTypeAdapter(
                        ContentList::class.java,
                        ContentAdapter()
                    ).create()
                )
            )
            .client(client)
            .build()
            .create(BonialApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBrochureRepository(api: BonialApi): BonialRepository {
        return BonialRepositoryImpl(api)
    }
}