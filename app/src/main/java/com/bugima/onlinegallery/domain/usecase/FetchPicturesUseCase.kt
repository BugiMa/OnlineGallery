package com.bugima.onlinegallery.domain.usecase

import com.bugima.onlinegallery.domain.model.Picture
import com.bugima.onlinegallery.domain.repository.PictureRepository
import com.bugima.onlinegallery.util.wrapper.Result
import javax.inject.Inject

class FetchPicturesUseCase @Inject constructor(
    private val repository: PictureRepository,
) {
    suspend fun execute(): Result<List<Picture>> {
        return repository.fetchPictures()
    }
}
