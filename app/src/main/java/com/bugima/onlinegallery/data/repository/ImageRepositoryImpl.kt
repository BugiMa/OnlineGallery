package com.bugima.onlinegallery.data.repository

import com.bugima.onlinegallery.data.remote.datasource.ImageDataSource
import com.bugima.onlinegallery.domain.model.Image
import com.bugima.onlinegallery.domain.repository.ImageRepository
import com.bugima.onlinegallery.util.Result
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageDataSource: ImageDataSource,
): ImageRepository {

    private val cachedImages = mutableListOf<Image>()

    override suspend fun fetchImages(): Result<List<Image>> {
        when(val result = imageDataSource.fetchImages()) {
            is Result.Success -> {
                cachedImages += result.data
                return Result.Success(cachedImages)
            }
            is Result.Failure -> return result
        }
    }

    override fun resetPagination() {
        cachedImages.clear()
        imageDataSource.resetPagination()
    }
}
