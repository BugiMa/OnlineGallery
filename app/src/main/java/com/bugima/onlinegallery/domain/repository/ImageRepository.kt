package com.bugima.onlinegallery.domain.repository

import com.bugima.onlinegallery.domain.model.Image
import com.bugima.onlinegallery.util.Result

interface ImageRepository {
    suspend fun fetchImages(): Result<List<Image>>
    fun resetPagination()
}
