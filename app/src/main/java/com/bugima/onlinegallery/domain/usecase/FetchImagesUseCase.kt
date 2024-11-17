package com.bugima.onlinegallery.domain.usecase

import com.bugima.onlinegallery.domain.model.Image
import com.bugima.onlinegallery.domain.repository.ImageRepository
import com.bugima.onlinegallery.util.Result
import javax.inject.Inject

class FetchImagesUseCase @Inject constructor(
    private val repository: ImageRepository,
) {
    suspend fun execute(): Result<List<Image>> {
        return repository.fetchImages()
    }
}
