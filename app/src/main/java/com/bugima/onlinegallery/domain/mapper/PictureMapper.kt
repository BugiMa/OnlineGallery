package com.bugima.onlinegallery.domain.mapper

import com.bugima.onlinegallery.data.remote.dto.PictureDto
import com.bugima.onlinegallery.domain.model.Picture

fun PictureDto.toDomain() = Picture(
    id = id,
    author = author,
    width = width,
    height = height,
    url = url,
    downloadUrl = downloadUrl
)
