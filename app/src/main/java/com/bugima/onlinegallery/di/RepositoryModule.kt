package com.bugima.onlinegallery.di

import com.bugima.onlinegallery.data.repository.ImageRepositoryImpl
import com.bugima.onlinegallery.domain.repository.ImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideCharacterRepository(
        repository: ImageRepositoryImpl
    ): ImageRepository
}