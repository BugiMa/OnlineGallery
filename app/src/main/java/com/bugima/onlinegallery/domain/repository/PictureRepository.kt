package com.bugima.onlinegallery.domain.repository

import com.bugima.onlinegallery.domain.model.Picture
import com.bugima.onlinegallery.util.wrapper.Result

interface PictureRepository {
    suspend fun fetchPictures(): Result<List<Picture>>
    fun resetPagination()
}
