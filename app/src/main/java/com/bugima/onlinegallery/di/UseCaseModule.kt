package com.bugima.onlinegallery.di

import com.bugima.onlinegallery.domain.repository.ImageRepository
import com.bugima.onlinegallery.domain.usecase.FetchImagesUseCase
import com.bugima.onlinegallery.domain.usecase.RefreshImagesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideFetchImagesUseCase(
        repository: ImageRepository
    ): FetchImagesUseCase {
        return FetchImagesUseCase(repository)
    }

    @Provides
    fun provideRefreshImagesUseCase(
        repository: ImageRepository
    ): RefreshImagesUseCase {
        return RefreshImagesUseCase(repository)
    }
}