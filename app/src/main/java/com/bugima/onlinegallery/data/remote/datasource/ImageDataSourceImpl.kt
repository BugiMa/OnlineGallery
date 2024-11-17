package com.bugima.onlinegallery.data.remote.datasource

import com.bugima.onlinegallery.data.remote.api.LoremPicsumApi
import com.bugima.onlinegallery.domain.mapper.toDomain
import com.bugima.onlinegallery.domain.model.Image
import com.bugima.onlinegallery.util.Const.INITIAL_PAGE
import com.bugima.onlinegallery.util.Const.NEXT_PAGE_URL
import com.bugima.onlinegallery.util.Result
import retrofit2.HttpException
import javax.inject.Inject

class ImageDataSourceImpl @Inject constructor(
    private val api: LoremPicsumApi,
): ImageDataSource {

    private var hasMorePages: Boolean = true
    private var nextPageUrl: String? = null

    override suspend fun fetchImages(): Result<List<Image>> {
        if (!hasMorePages) return Result.Failure(Exception("NoMoreToLoad")) //TODO: Custom Exception

        val response = nextPageUrl?.let { url ->
            api.fetchImages(url)
        } ?: api.fetchImages(INITIAL_PAGE)

        return response.takeIf { it.isSuccessful }?.body()?.let { body ->
            nextPageUrl = response.headers()[NEXT_PAGE_URL]
            hasMorePages = nextPageUrl != null
            Result.Success(body.map { it.toDomain() })
        } ?: Result.Failure(HttpException(response))
    }

    override fun resetPagination() {
        nextPageUrl = null
        hasMorePages = true
    }
}
