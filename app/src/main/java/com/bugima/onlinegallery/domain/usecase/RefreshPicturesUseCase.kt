package com.bugima.onlinegallery.domain.usecase

import com.bugima.onlinegallery.domain.repository.PictureRepository
import javax.inject.Inject

class RefreshPicturesUseCase @Inject constructor(
    private val repository: PictureRepository,
) {
    fun execute() {
        repository.resetPagination()
    }
}
