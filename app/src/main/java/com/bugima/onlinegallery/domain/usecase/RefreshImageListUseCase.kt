package com.bugima.onlinegallery.domain.usecase

import com.bugima.onlinegallery.domain.repository.ImageRepository
import javax.inject.Inject

class RefreshImagesUseCase @Inject constructor(
    private val repository: ImageRepository,
) {
    fun execute() {
        repository.resetPagination()
    }
}
