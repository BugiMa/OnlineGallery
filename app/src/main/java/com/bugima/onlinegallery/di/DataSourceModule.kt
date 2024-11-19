package com.bugima.onlinegallery.di

import com.bugima.onlinegallery.data.remote.api.LoremPicsumApi
import com.bugima.onlinegallery.data.remote.datasource.PictureDataSource
import com.bugima.onlinegallery.data.remote.datasource.PictureDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideCharacterDataSource(api: LoremPicsumApi): PictureDataSource {
        return PictureDataSourceImpl(api)
    }
}
