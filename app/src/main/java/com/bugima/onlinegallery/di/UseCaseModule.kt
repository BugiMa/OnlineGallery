package com.bugima.onlinegallery.di

import com.bugima.onlinegallery.domain.repository.PictureRepository
import com.bugima.onlinegallery.domain.usecase.FetchPicturesUseCase
import com.bugima.onlinegallery.domain.usecase.RefreshPicturesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideFetchImagesUseCase(
        repository: PictureRepository
    ): FetchPicturesUseCase {
        return FetchPicturesUseCase(repository)
    }

    @Provides
    fun provideRefreshImagesUseCase(
        repository: PictureRepository
    ): RefreshPicturesUseCase {
        return RefreshPicturesUseCase(repository)
    }
}