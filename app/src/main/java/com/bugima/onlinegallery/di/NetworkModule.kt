package com.bugima.onlinegallery.di

import com.bugima.onlinegallery.BuildConfig.API_BASE_URL
import com.bugima.onlinegallery.data.remote.api.LoremPicsumApi
import com.bugima.onlinegallery.data.remote.interceptor.PaginationInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providePaginationInterceptor(): PaginationInterceptor {
        return PaginationInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(paginationInterceptor: PaginationInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(paginationInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): LoremPicsumApi {
        return retrofit.create(LoremPicsumApi::class.java)
    }
}
