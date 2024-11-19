package com.bugima.onlinegallery.data.repository

import com.bugima.onlinegallery.data.remote.datasource.PictureDataSource
import com.bugima.onlinegallery.domain.model.Picture
import com.bugima.onlinegallery.domain.repository.PictureRepository
import com.bugima.onlinegallery.util.wrapper.Result
import javax.inject.Inject

class PictureRepositoryImpl @Inject constructor(
    private val pictureDataSource: PictureDataSource,
) : PictureRepository {

    private val cachedPictures = mutableListOf<Picture>()

    override suspend fun fetchPictures(): Result<List<Picture>> {
        when (val result = pictureDataSource.fetchPictures()) {
            is Result.Success -> {
                cachedPictures += result.data
                return Result.Success(cachedPictures)
            }
            is Result.Failure -> return result
        }
    }

    override fun resetPagination() {
        cachedPictures.clear()
        pictureDataSource.resetPagination()
    }
}
