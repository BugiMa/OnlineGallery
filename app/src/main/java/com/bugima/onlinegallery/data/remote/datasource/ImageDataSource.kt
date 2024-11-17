package com.bugima.onlinegallery.data.remote.datasource

import com.bugima.onlinegallery.domain.model.Image
import com.bugima.onlinegallery.util.Result

interface ImageDataSource {
    suspend fun fetchImages(): Result<List<Image>>
    fun resetPagination()
}
