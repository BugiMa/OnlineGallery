package com.bugima.onlinegallery.data.remote.api

import com.bugima.onlinegallery.data.remote.dto.PictureDto
import com.bugima.onlinegallery.util.Const
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface LoremPicsumApi {
    @GET("v2/list")
    suspend fun fetchPictures(
        @Query("page") page: Int,
        @Query("limit") limit: Int = Const.PAGE_SIZE,
    ): Response<List<PictureDto>>

    @GET
    suspend fun fetchPictures(
        @Url pageUrl: String,
    ): Response<List<PictureDto>>
}
