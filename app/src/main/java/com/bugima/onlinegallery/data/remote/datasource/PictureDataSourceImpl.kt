package com.bugima.onlinegallery.data.remote.datasource

import com.bugima.onlinegallery.data.remote.api.LoremPicsumApi
import com.bugima.onlinegallery.domain.mapper.toDomain
import com.bugima.onlinegallery.domain.model.Picture
import com.bugima.onlinegallery.util.AppException
import com.bugima.onlinegallery.util.Const.INITIAL_PAGE
import com.bugima.onlinegallery.util.Const.NEXT_PAGE_URL
import com.bugima.onlinegallery.util.wrapper.Result
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class PictureDataSourceImpl @Inject constructor(
    private val api: LoremPicsumApi,
) : PictureDataSource {

    private var hasMorePages: Boolean = true
    private var nextPageUrl: String? = null

    override suspend fun fetchPictures(): Result<List<Picture>> {
        if (!hasMorePages) return Result.Failure(AppException.NoMoreDataException)

        return try {
            val response = nextPageUrl?.let { url ->
                api.fetchPictures(url)
            } ?: api.fetchPictures(INITIAL_PAGE)

            response.takeIf { it.isSuccessful }?.body()?.let { body ->
                nextPageUrl = response.headers()[NEXT_PAGE_URL]
                hasMorePages = nextPageUrl != null
                Result.Success(body.map { it.toDomain() })
            } ?: Result.Failure(AppException.ServerException(HttpException(response)))
        }
        catch (e: IOException) { Result.Failure(AppException.NetworkException(e)) }
        catch (e: HttpException) { Result.Failure(AppException.ServerException(e)) }
        catch (e: Exception) { Result.Failure(AppException.UnknownException(e)) }
    }

    override fun resetPagination() {
        nextPageUrl = null
        hasMorePages = true
    }
}
