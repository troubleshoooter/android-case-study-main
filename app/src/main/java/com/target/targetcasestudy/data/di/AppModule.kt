package com.target.targetcasestudy.data.di

import com.squareup.moshi.Moshi
import com.target.targetcasestudy.BuildConfig
import com.target.targetcasestudy.data.source.remote.api.DealApiKtx
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        } else OkHttpClient.Builder()
            .build()
    }

    @Singleton
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    fun getApiService(okHttpClient: OkHttpClient, mosh: Moshi): DealApiKtx {
        return Retrofit.Builder()
            .baseUrl("https://api.target.com/mobile_case_study_deals/v1/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(mosh))
            .build()
            .create(DealApiKtx::class.java)
    }

}