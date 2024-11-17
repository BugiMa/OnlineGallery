package com.bugima.onlinegallery.domain.mapper

import com.bugima.onlinegallery.data.remote.dto.ImageDto
import com.bugima.onlinegallery.domain.model.Image

fun ImageDto.toDomain() = Image(
    id = id,
    author = author,
    width = width,
    height = height,
    url = url,
    downloadUrl = downloadUrl
)
