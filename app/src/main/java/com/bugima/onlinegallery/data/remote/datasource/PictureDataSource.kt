package com.bugima.onlinegallery.data.remote.datasource

import com.bugima.onlinegallery.domain.model.Picture
import com.bugima.onlinegallery.util.wrapper.Result

interface PictureDataSource {
    suspend fun fetchPictures(): Result<List<Picture>>
    fun resetPagination()
}
