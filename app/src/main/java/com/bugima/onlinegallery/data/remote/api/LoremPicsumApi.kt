package com.bugima.onlinegallery.data.remote.api

import com.bugima.onlinegallery.data.remote.dto.ImageDto
import com.bugima.onlinegallery.util.Const
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface LoremPicsumApi {
    @GET("v2/list")
    suspend fun fetchImages(
        @Query("page") page: Int,
        @Query("limit") limit: Int = Const.PAGE_SIZE,
    ): Response<List<ImageDto>>

    @GET
    suspend fun fetchImages(
        @Url pageUrl: String,
    ): Response<List<ImageDto>>
}
